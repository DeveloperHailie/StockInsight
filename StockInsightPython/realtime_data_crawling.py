from datetime import datetime 
import time
import FinanceDataReader as fdr
import pymysql
import csv
import os 
import sys
import math
from DBInfo import DB

# 차이% 보고 보합 구하는 함수 
def getGap(change) :
    change = change*100
    if change < 1.0 and change > 0.0 :
        gap = "보합"
    elif change >= 1.0:
        gap = "상승"
    else:
        gap = "하락"
    return gap;

# row 개수 세는 함수
def countRow(fileName) :
    if(os.path.isfile(fileName)) :
        return len(open(fileName).readlines()) 
    return 0

def roundChange(change) :
    return round(change,2)


# 장 열려있지 않으면 바로 종료
n = time.localtime().tm_wday
n_tm = time.localtime() # 현재 시간 구하기

if(n==5 or n==6) : # 토요일 혹은 일요일이면
    sys.exit()
# now.tm_hour, now.tm_min
if(n_tm.tm_hour<9 or n_tm.tm_hour>16) :
    sys.exit()
elif(n_tm.tm_hour==15 and n_tm.tm_min>30) :
    sys.exit()  
  

dbInformation = DB()
# DB 연결
connect = pymysql.connect(host=dbInformation.host, port=dbInformation.port, 
                          user=dbInformation.user, password=dbInformation.password, db=dbInformation.dbName)

cursor = connect.cursor()

# DB에서 사진 url 가져오기
select_sql = "SELECT stock_code, realtime_data, stock_company FROM Stock"
cursor.execute(select_sql)
rows = cursor.fetchall()

errorCode = []
krx_data = list()

#한국거래소 상장종목 전체
df_krx_code = fdr.StockListing('KRX')['Symbol']


# csv : date, 현재가격, 부호, 상승/하락(원), 거래량
for row in rows :

    code = row[0]
    
    fileName= "C:\\StockInsightPython\\realtimeData\\"+row[1]
    
    # 코드, 가격, 상승/하락, 상승/하락(원), 상승/하락(퍼센트)    
    if code == "000000" or code == "111111" : # 코스피, 코스닥인 경우
        continue
    else :
        data = []
        krx_data = []
        # 일반 종목인 경우
        df = fdr.DataReader(code, n_tm.tm_year)
        
        df = df.tail(1)
        
        df_close = df.iloc[0, 3] # Close - 현재 가격 
        df_volume = df.iloc[0, 4] # Volume - 거래량 
        df_change = df.iloc[0, 5] # Change - 차이
        df_sign = getGap(df_change) # Sign - 부호
        
        df_change = roundChange(df_change*100)

        krx_data.append(str(df_close))
        krx_data.append(df_sign)
        krx_data.append(str(df_change))
        krx_data.append(str(df_volume))
        
        
        data = krx_data
        
        if(data==[]) : # 받아오지 못하면 
            errorCode.append(code)
            continue
        
        # csv row 개수 구하기
        count = countRow(fileName)
        if(count==0) : # 파일 존재하지 않을 시
            f = open(fileName, 'w', newline = '')
            wr = csv.writer(f)
        else : # 파일 존재 시
            f = open(fileName,'a', newline='') # 파일 수정
            wr = csv.writer(f)
            if count >= 78 : # 파일 최대 row 초과 시
                # 상위 row 삭제
                f1 = open(fileName,'r+', newline='') # 파일 수정
                l = f1.readlines()
                del l[0]
                f1.close()
                f.close()
                f = open(fileName,'a', newline='') # 파일 수정
                wr = csv.writer(f)

        # 하위 row 추가
        now = time.localtime() # 현재 시간 구하기
        nowDate = "%04d/%02d/%02d %02d:%02d:%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
        data[2] = "(" + data[2] +"%)"
        wr.writerow([nowDate,data[0],data[1],data[2],data[3]])
     
        
        f.close()
        
        # Stock 테이블의 현재가 업데이트
        cursor.execute("UPDATE Stock SET stock_volume = '" + str(data[3]) +"'" + " WHERE stock_code = "+code)
        cursor.execute("UPDATE Stock SET stock_before ='" + str(data[0]) +"'" + " WHERE stock_code = "+code)
        connect.commit()    


connect.close()