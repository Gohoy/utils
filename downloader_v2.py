   
# 直接从html获取的数据不够全面，需要从js中获取 key为__NEXT_DATA__
 # 这是__NEXT_DATA__的结构
    # 这是这个work的所有章节id
    # print(data["props"]["pageProps"]["__APOLLO_STATE__"]["Work:1177354054880950791"]["tableOfContents"])


    # 这是每个章节的文章id
    # print(data["props"]["pageProps"]["__APOLLO_STATE__"]["TableOfContentsChapter:1177354054881102840"]["episodeUnions"])

    

#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import io
import requests
from html.parser import HTMLParser
from datetime import datetime
from bs4 import BeautifulSoup
import json
import re  # 导入 re 模块

# 设置输出编码为 UTF-8
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
# 全局变量
url_prefix = "https://kakuyomu.jp"
separator = "▲▼▲▼▲▼▲▼▲▼▲▼▲▼▲▼▲▼▲▼▲▼▲▼▲▼\n"
user_agent = 'Mozilla/5.0 (X11; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0'
charcode = 'utf-8'
work_id= ""
if sys.platform.startswith('win'):
    charcode = 'cp932'


def extract_js(html):
    """提取 JavaScript 部分"""
    soup = BeautifulSoup(html, 'html.parser')
    js_script = soup.find('script', id='__NEXT_DATA__')
    if js_script:
        return js_script.string
    else:
        return ""


def honbun(text):
    """处理正文"""
    soup = BeautifulSoup(text, 'html.parser')
    paragraphs = soup.find_all('p', {'id': re.compile(r'p\d+')})
    result = ""
    for paragraph in paragraphs:
        result += paragraph.get_text(strip=True) + "\n"
    return result

def get_contents(address):
    """从URL获取内容"""
    headers = {'User-Agent': user_agent}
    response = requests.get(address, headers=headers)
    if response.status_code == 200:
        return response.text
    else:
        raise Exception("Error: {} - {}".format(response.status_code, response.reason))

def get_all(urlList):
    """获取所有章节"""
    for url in urlList:
        body = get_contents(url)
        text = honbun(body)
        print(text)


def getIndex(json):
    chapterList = json["props"]["pageProps"]["__APOLLO_STATE__"]["Work:"+work_id]["tableOfContents"]
    articleList = []

    for item in chapterList:
        chapterId = item['__ref']
        # print(chapterId)
        articleString = json["props"]["pageProps"]["__APOLLO_STATE__"][chapterId]["episodeUnions"]
        for article in articleString:
            articleId = article["__ref"].split(':')[1]
            articleList.append(url_prefix+"/works/"+work_id+"/episodes/" +articleId)
    return articleList


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("用法：python kakuyomu-dl.py [目录url] > [保存文件]")
        sys.exit(1)
    url = sys.argv[1]
    work_id= url[url.find("works/")+ len("works/"):]
    
    body = get_contents(url)
    jsonString = json.loads(extract_js(body))

    urlList = getIndex(jsonString)
    get_all(urlList)