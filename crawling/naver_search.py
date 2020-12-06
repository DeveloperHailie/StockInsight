from selenium import webdriver
import time

# 회사별 정보 얻기
def getStock(code):
    # 필요 정보 위치 (하락일 때)
    realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > strong'
    gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > span.ico'
    gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(3)'
    gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(4)'
    high = '#_cs_root > div.ar_cont > div.cont_dtcon > div > ul.lst > li.hp > dl > dd'
    low = '#_cs_root > div.ar_cont > div.cont_dtcon > div > ul.lst > li.lp > dl > dd'
    volume = '#_cs_root > div.ar_cont > div.cont_dtcon > div > ul.lst > li.vl > dl > dd'
    selector = [realPrice, gap, gapMoney, gapPer, high, low, volume]
    name = ['실시간 가격', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)', '고가', '저가', '거래량']
    
    
    
    # 드라이버 열기
    driver = webdriver.Chrome()
    site = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=' + code
    driver.get(site)
    time.sleep(2)
    
    try :
        result = [code]
        for i in range(0, 7) :
            sample = driver.find_elements_by_css_selector(selector[i])
            sample_data = sample[0].text
            result.append(sample_data)
            print(name[i], sample_data)
        
        driver.close()
        return result
    except : 
        driver.close()
        realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > strong'
        gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > span.ico'
        gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > em:nth-child(3)'
        gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.up > span.n_ch > em:nth-child(4)'
        selector = [realPrice, gap, gapMoney, gapPer, high, low, volume]
        
        driver = webdriver.Chrome()
        site = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=' + code
        driver.get(site)
        time.sleep(2)
        
        try : 
            result = [code]
            for i in range(0, 7) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                print(name[i], sample_data)
            
            driver.close()
            return result
        except :
            driver.close()
            realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > strong'
            gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > span.ico'
            gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(3)'
            gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(4)'
            
            selector = [realPrice, gap, gapMoney, gapPer, high, low, volume]
        
            driver = webdriver.Chrome()
            site = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=' + code
            driver.get(site)
            time.sleep(2)
            
            result = [code]
            for i in range(0, 7) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                print(name[i], sample_data)
            
            driver.close()
            return result
        
        
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
    driver = webdriver.Chrome()
    site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%ED%94%BC&oquery=105560&tqi=U8u%2Bmdp0YihssBacb28ssssssro-153798'
    driver.get(site)
    time.sleep(2)
    
    try :
        result = [code]
        for i in range(0, 4) :
            sample = driver.find_elements_by_css_selector(selector[i])
            sample_data = sample[0].text
            result.append(sample_data)
            print(name[i], sample_data)
        
        driver.close()
        return result
    except :
        realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > strong'
        gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > span.ico'
        gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(3)'
        gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(4)'
        selector = [realPrice, gap, gapMoney, gapPer]
        name = ['코스피', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
        
        # 드라이버 열기, 네이버
        driver = webdriver.Chrome()
        site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%ED%94%BC&oquery=105560&tqi=U8u%2Bmdp0YihssBacb28ssssssro-153798'
        driver.get(site)
        time.sleep(2)
        
        try :
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                print(name[i], sample_data)
            
            driver.close()
            return result
        except : 
            realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > strong'
            gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > span.ico'
            gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(3)'
            gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(4)'
            selector = [realPrice, gap, gapMoney, gapPer]
            name = ['코스피', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
            
            # 드라이버 열기, 네이버
            driver = webdriver.Chrome()
            site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%ED%94%BC&oquery=105560&tqi=U8u%2Bmdp0YihssBacb28ssssssro-153798'
            driver.get(site)
            time.sleep(2)
            
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                print(name[i], sample_data)
            
            driver.close()
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
    driver = webdriver.Chrome()
    site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%EB%8B%A5&oquery=%EC%BD%94%EC%8A%A4ekr&tqi=U8vZzdprvxsssDX4AFKsssssshR-015335'
    driver.get(site)
    time.sleep(2)
    
    try : 
        result = [code]
        for i in range(0, 4) :
            sample = driver.find_elements_by_css_selector(selector[i])
            sample_data = sample[0].text
            result.append(sample_data)
            print(name[i], sample_data)
        
        driver.close()
        return result
    except :
        realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > strong'
        gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > span.ico'
        gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(3)'
        gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.dw > span.n_ch > em:nth-child(4)'
        
        selector = [realPrice, gap, gapMoney, gapPer]
        name = ['코스닥', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
        
        # 드라이버 열기, 네이버
        driver = webdriver.Chrome()
        site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%EB%8B%A5&oquery=%EC%BD%94%EC%8A%A4ekr&tqi=U8vZzdprvxsssDX4AFKsssssshR-015335'
        driver.get(site)
        time.sleep(2)
        
        try:
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                print(name[i], sample_data)
            
            driver.close()
            return result
        except : 
            realPrice = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > strong'
            gap = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > span.ico'
            gapMoney = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(3)'
            gapPer = '#_cs_root > div.ar_spot > div > h3 > a > span.spt_con.eq > span.n_ch > em:nth-child(4)'
            selector = [realPrice, gap, gapMoney, gapPer]
            
            name = ['코스닥', '상승/하락', '상승/하락(원)', '상승/하락(퍼센트)']
        
            # 드라이버 열기, 네이버
            driver = webdriver.Chrome()
            site = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EC%8A%A4%EB%8B%A5&oquery=%EC%BD%94%EC%8A%A4ekr&tqi=U8vZzdprvxsssDX4AFKsssssshR-015335'
            driver.get(site)
            time.sleep(2)
            
            result = [code]
            for i in range(0, 4) :
                sample = driver.find_elements_by_css_selector(selector[i])
                sample_data = sample[0].text
                result.append(sample_data)
                print(name[i], sample_data)
            
            driver.close()
            return result


code = input("종목코드:")
#네이버 '105560'
#삼성 '005930'
#하이트진로홀딩스 '000140'
result = getStock(code) 
result2 = getKospi()
result3 = getKosdap()