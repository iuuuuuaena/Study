# from  urllib.request import urlopen
import urllib.error as error

#from bs4 import BeautifulSoup

import csv
from selenium import webdriver

import ssl

ssl._create_default_https_context = ssl._create_unverified_context
# ssl认证证书


# try:
# html = urlopen('http://music.163.com/#/discover/playlist')
# #打开url，获取html

# bs_obj = BeautifulSoup(html.read(),'html.parser')
# #把html传到beautifulsoup对象



# text_list = bs_obj.find_all('span','nb')
# #找到所有class = “” 的连接a

# for text in text_list:
#     print(text.get_text())

# html.close()

# except error.URLError as err:
#     print(err)

if __name__ == '__main__':
    url = "https://music.163.com/#/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0"
    driver = webdriver.PhantomJS()

    csv_list = open("music1.csv", "w", newline="")
    # 打开存放数据的csv文件

    writer = csv.writer(csv_list)
    writer.writerow(['标题 ', '播放量', '链接'])

    # j解析每一页，直到下一页为空
    while url != 'javascript:void(0)':
        # 用webdriver加载页面
        driver.get(url)
        # 切换到内容的iframe
        driver.switch_to.frame('contentFrame')
        # 定位到歌单的
        data1 = driver.find_element_by_id("m-pl-container").\
            find_element_by_tag_name("li")

        for i in range(len(data1)):
            nb = data1[i].find_element_by_class_name("nb").text
            if '万' in nb and int(nb.split("万")[0]) > 500:
                # 获取当前大于500万播放量的歌单
                msk = data1[i].find_element_by_css_selector("a.msk")

                writer.writerow([msk.get_attribute('title'), nb, msk.get_attribute('href')])
        url = driver.find_elements_by_css_selector('a.zbtn,znxt').\
            get_attribute('href')
    csv_list.close
# print (html.read