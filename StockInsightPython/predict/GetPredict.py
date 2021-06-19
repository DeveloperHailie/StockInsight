import numpy as np
import cv2
from urllib.request import urlopen
import tensorflow as tf
import FinanceDataReader as fdr
from sklearn.preprocessing import MinMaxScaler
import pandas as pd
import csv
import os
import pymysql
import tkinter 
from datetime import datetime 
import time
import sys
from DBInfo import DB

# 장 열려있지 않으면 바로 종료
n = time.localtime().tm_wday
n_tm = time.localtime() # 현재 시간 구하기

if(n==5 or n==6) : # 토요일 혹은 일요일이면
    sys.exit()
# now.tm_hour, now.tm_min
if(n_tm.tm_hour<9 or n_tm.tm_hour>16) :
    sys.exit()

    

dbInformation = DB()
# DB 연결
connect = pymysql.connect(host=dbInformation.host, port=dbInformation.port, 
                          user=dbInformation.user, password=dbInformation.password, db=dbInformation.dbName)



#cursor 객체 생성
cursor = conn.cursor()


#모델 로드 함수
def loadModel(model) :
    np.set_printoptions(suppress=True)
    classifierLoad = tf.keras.models.load_model(model)
    return classifierLoad 

def predict(code) :
    df = fdr.DataReader(df_krx_stock, '2020')
            
    source = df.tail(5) # 최근 5일치 api 
    source = source[['Open', 'High', 'Low', 'Close', 'Volume']]
    source = source.values
    
    transformer = MinMaxScaler()
    train = transformer.fit_transform(source)
    train = np.array([train])
    y = model.predict(train)
                
    y_inverse = transformer.inverse_transform([[0, 0, 0, y[0], 0]])
    predict_close = y_inverse[0][3]
    
    return predict_close
    

# model path 설정 및 model load
model_path = "C:\\StockInsightPython\\predict\\DeepLearningModel.h5"
model = loadModel(model_path)

#한국거래소 상장종목 전체
df_krx_code = fdr.StockListing('KRX')['Symbol'] # 업종 코드  
df_krx_name = fdr.StockListing('KRX')['Name'] # 회사명 


# csv 저장할 list 
c = list()
check = True

# 모델 가지고 예측값 구하기 
for df_krx_stock in df_krx_code:
    #source는 5일간의 데이터
    
    if(os.path.isfile("..\\realtimeData\\"+df_krx_stock + ".csv")) : # 파일 있을 시 
        check = True
    else : # 파일 없을 시 
        check = False
    
    # csv 받아오기   
    if(check == True) : 
        try :
            
            df_today = pd.read_csv("..\\realtimeData\\"+df_krx_stock + ".csv", header=None) # today dateframe(가격)
            
            len(df_today) # csv row 갯수 
            df_close_list = df_today[1] # today close 열 list
            
            df_open = df_close_list[0] # 현재가격 Open 시가
            df_close = df_close_list[len(df_today)-1] # 현재가격 Close 종가 
            
            df_volume = df_today[4][len(df_today)-1] # 현재가격 거래량 
            
            
            # csv 값 list에 넣기 
            for x in df_close_list:
                c.append(x) # 현재가격 colume 받아오기 
                
            # 정렬 후 - 내림차순 정렬 
            c.sort()    
            df_low = c[0] # Low - 저가
            df_high = c[len(c)-1] #High -  고가
            
            df = fdr.DataReader(df_krx_stock, '2020')
            
            
            if df.empty : # dataframe none 확인
                continue
            else :   
                source = df.tail(5) # 최근 5일치 api 
                source = source[['Open', 'High', 'Low', 'Close', 'Volume']]
                source = source.values
                
                
                ## update 할 내용이 있을 때 
                source = np.delete(source,0, axis=0)
                
                source = np.insert(source,4, [df_open, df_high, df_low, df_close, df_volume], axis=0) 
                ##
                
                transformer = MinMaxScaler()
                train = transformer.fit_transform(source)
                train = np.array([train])
                y = model.predict(train)
                
                y_inverse = transformer.inverse_transform([[0, 0, 0, y[0], 0]])
                predict_close = y_inverse[0][3]
            
            c = [] # 초기화 
            
            
        except:
            predict_close = predict(df_krx_stock)
         
    else :
        try:
            predict_close = predict(df_krx_stock)
        except:
            continue

     
    #DB 업데이트      
    try:
        sql = "UPDATE Stock SET stock_future = '"+str(predict_close)+"' WHERE stock_code ="+(df_krx_stock)
        cursor.execute(sql)
        conn.commit()     
    except:
        print("error")
            
conn.close()

            

    
