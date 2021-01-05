from selenium import webdriver
import time
import pymysql
import time
import csv
import os 
import sys
from DBInfo import DB

# 코스피 정보 얻기
def getKospi():
    # 필요 정보 위치
    realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > strong'
    gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > span.ico'
    gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > em:nth-child(3)'
    gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > em:nth-child(4)'
    selector = [realPrice, gap, gapMoney, gapPer]
    name = ['코스피', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
    
    # 드라이버 열기, 네이버
    site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%ED%94%BC&oquery=105560&tqi=U8u%2Bmdp0YihssBacb28ssssssro-153798'
    driver.get(site)
    time.sleep(2)
    
    code = "000000"
    try :
        result = [code]
        for i in range(0, 4) :
            sample = driver.find_elements_by_css_selector(selector[i])
            sample_data = sample[0].text
            result.append(sample_data)

        
        return result
    except :
        realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > strong'
        gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > span.ico'
        gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(3)'
        gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(4)'
        selector = [realPrice, gap, gapMoney, gapPer]
        name = ['코스피', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
        
        # 드라이버 열기, 네이버
        site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%ED%94%BC&oquery=105560&tqi=U8u%2Bmdp0YihssBacb28ssssssro-153798'
        driver.get(site)
        time.sleep(2)
        
        try :
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                
            
            return result
        except : 
            realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > strong'
            gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > span.ico'
            gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(3)'
            gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(4)'
            selector = [realPrice, gap, gapMoney, gapPer]
            name = ['코스피', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
            
            # 드라이버 열기, 네이버
            site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%ED%94%BC&oquery=105560&tqi=U8u%2Bmdp0YihssBacb28ssssssro-153798'
            driver.get(site)
            time.sleep(2)
            
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                
            
            return result

# 코스닥 정보 얻기
def getKosdap():
    # 필요 정보 위치
    realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > strong'
    gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > span.ico'
    gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > em:nth-child(3)'
    gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > em:nth-child(4)'
    selector = [realPrice, gap, gapMoney, gapPer]
    name = ['코스닥', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
    
    # 드라이버 열기, 네이버
    site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%EB%8B%A5&oquery=%EC%BD%94%EC%8A%A4ekr&tqi=U8vZzdprvxsssDX4AFKsssssshR-015335'
    driver.get(site)
    time.sleep(2)
    
    code = "111111"
    try : 
        result = [code]
        for i in range(0, 4) :
            sample = driver.find_elements_by_css_selector(selector[i])
            sample_data = sample[0].text
            result.append(sample_data)
   
        
        return result
    except :
        realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > strong'
        gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > span.ico'
        gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(3)'
        gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(4)'
        
        selector = [realPrice, gap, gapMoney, gapPer]
        name = ['코스닥', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
        
        # 드라이버 열기, 네이버
        site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%EB%8B%A5&oquery=%EC%BD%94%EC%8A%A4ekr&tqi=U8vZzdprvxsssDX4AFKsssssshR-015335'
        driver.get(site)
        time.sleep(2)
        
        try:
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                
            
            return result
        except : 
            realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > strong'
            gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > span.ico'
            gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(3)'
            gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(4)'
            selector = [realPrice, gap, gapMoney, gapPer]
            
            name = ['코스닥', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
        
            # 드라이버 열기, 네이버
            site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%EB%8B%A5&oquery=%EC%BD%94%EC%8A%A4ekr&tqi=U8vZzdprvxsssDX4AFKsssssshR-015335'
            driver.get(site)
            time.sleep(2)
            
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                
            return result

# row 개수 세는 함수
def countRow(fileName) :
    if(os.path.isfile(fileName)) :
        return len(open(fileName).readlines()) 
    return 0

# 데이터 ,빼기 (실시간가격, 상승/하락(원), 거래량)에 적용 필요
def convertNum(string):
    string = string.replace(',','')
    return string


# 장 열려있지 않으면 바로 종료
n = time.localtime().tm_wday
n_tm = time.localtime() # 현재 시간 구하기

if(n==5 or n==6) : # 토요일 혹은 일요일이면
    sys.exit()
# now.tm_hour, now.tm_min
if(n_tm.tm_hour<9 or n_tm.tm_hour>=16) : # 0~8, 16~
    sys.exit()
elif(n_tm.tm_hour==15 and n_tm.tm_min>30) :
    sys.exit()

driver = webdriver.Chrome('C:\\StockInsightPython\\chromedriver.exe')


dbInformation = DB()
# DB 연결
connect = pymysql.connect(host=dbInformation.host, port=dbInformation.port, 
                          user=dbInformation.user, password=dbInformation.password, db=dbInformation.dbName)

cursor = connect.cursor()

# DB에서 가져오기
select_sql = "SELECT stock_code, realtime_data, stock_company FROM Stock WHERE stock_company = '코스피'"
cursor.execute(select_sql)
rows = cursor.fetchall()

errorCode = []

# 코드, 가격, 상승/하락, 상승/하락(원), 상승/하락(퍼센트)
# csv : date, 현재가격, 부호, 상승/하락(원), 거래량
for row in rows :
    code = row[0]
    fileName= "C:\\StockInsightPython\\realtimeData\\"+row[1]
    
    data = getKospi()
    count = countRow(fileName)
    if(count == 0) : # 파일 존재하지 않을 시
        f = open(fileName,'w', newline='') # 파일 생성
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
    wr.writerow([nowDate,convertNum(data[1]),data[2],data[4],"0"])
    f.close()
    # Stock 테이블의 현재가 업데이트
    cursor.execute("UPDATE Stock SET stock_volume = '" + "0" +"'" + " WHERE stock_code = "+code)
    connect.commit()
    cursor.execute("UPDATE Stock SET stock_before ='" + convertNum(data[1]) +"'" + " WHERE stock_code = "+code)
    connect.commit()
    
# DB에서 가져오기
select_sql = "SELECT stock_code, realtime_data, stock_company FROM Stock WHERE stock_company = '코스닥'"
cursor.execute(select_sql)
rows = cursor.fetchall()

# 코드, 가격, 상승/하락, 상승/하락(원), 상승/하락(퍼센트)
# csv : date, 현재가격, 부호, 상승/하락(원), 거래량
for row in rows :
    
    code = row[0]
    fileName= "C:\\StockInsightPython\\realtimeData\\"+row[1]
    
    data = getKosdap()
    count = countRow(fileName)
    if(count == 0) : # 파일 존재하지 않을 시
        f = open(fileName,'w', newline='') # 파일 생성
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
    wr.writerow([nowDate,convertNum(data[1]),data[2],data[4],"0"])
    f.close()
    # Stock 테이블의 현재가 업데이트
    cursor.execute("UPDATE Stock SET stock_volume = '" + "0" +"'" + " WHERE stock_code = "+code)
    connect.commit()
    cursor.execute("UPDATE Stock SET stock_before ='" + convertNum(data[1]) +"'" + " WHERE stock_code = "+code)
    connect.commit() 
        
   

driver.close()
connect.close()