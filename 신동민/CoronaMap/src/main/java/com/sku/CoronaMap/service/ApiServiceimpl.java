package com.sku.CoronaMap.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class ApiServiceimpl implements ApiService{

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    @Override
    public List<String> CallCoronaApi(String start_date, String end_date) {
        int page = 1;	// 페이지 초기값
        List<String> result = new ArrayList<> ();

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //Calendar c1 = Calendar.getInstance();
        //String strToday = sdf.format(c1.getTime()); // 오늘 날짜입력
        //System.out.println("Today=" + strToday);

        try {
            StringBuilder url = new StringBuilder ("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson");
            url.append ("?" + URLEncoder.encode ("ServiceKey", "UTF-8") + "=AYVQTVFCUQ0Wda6v9brkZWrDRd2GBdfajmkkf0CLCdJVAuU2N0gz2S%2BmQvPNBlItSekbZy4ek%2BI3n7JZ3AIVYA%3D%3D");
            url.append ("&" + URLEncoder.encode ("pageNo", "UTF-8") + "=" + URLEncoder.encode ("1", "UTF-8"));
            url.append ("&" + URLEncoder.encode ("numOfRows", "UTF-8") + "=" + URLEncoder.encode ("10", "UTF-8"));
            url.append ("&" + URLEncoder.encode ("startCreateDt", "UTF-8") + "=" + URLEncoder.encode (start_date, "UTF-8"));
            url.append ("&" + URLEncoder.encode ("endCreateDt", "UTF-8") + "=" + URLEncoder.encode (end_date, "UTF-8"));

            while(true){
                DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = DBFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url.toString ()); // url 문서를 가지고옴

                // root tag
                //doc.getDocumentElement().normalize();
                //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());  // 파싱할 리스트 수 = 총 item 수

                for(int temp = 0; temp < nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){
                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());

                        result.add ("######################");
                        result.add ("사망자   : " + getTagValue ("deathCnt", eElement));
                        result.add ("확진자 수 : " + getTagValue ("defCnt", eElement));
                        result.add ("시도명   : " + getTagValue ("gubun", eElement));
                        result.add ("증감 수  : " + getTagValue ("incDec", eElement));
                        result.add ("해제 수  : " + getTagValue ("isolClearCnt", eElement));
                        result.add ("국내 수  : " + getTagValue ("localOccCnt", eElement));
                        result.add ("국외 수  : " + getTagValue ("overFlowCnt", eElement));
                        result.add ("10만명당 수 : " + getTagValue ("qurRate", eElement));
                        result.add ("국외 수  : " + getTagValue ("overFlowCnt", eElement));
                    }	// for end
                }	// if end

                page += 1;
                //System.out.println("page number : "+page);
                if(page > 1){
                    break;
                }
            }	// while end

        } catch (Exception e) {
            e.printStackTrace ();
        }
        return result;
    }
}
/*
<createDt>2020-04-10 09:00:00.000</createDt>  // 시작 날짜
<deathCnt>208</deathCnt>			  // 사망자
<defCnt>10450</defCnt>			  // 확진자 수
<gubun>합계</gubun>  			  // 시도명
<gubunCn>合计</gubunCn>			 // 중국어
<gubunEn>Total</gubunEn>			 // 영어
<incDec>27</incDec>			// 전일대비 증감 수
<isolClearCnt>6871</isolClearCnt>                 // 격리 해제 수
<localOccCnt>17</localOccCnt>		// 지역발생 수
<overFlowCnt>10</overFlowCnt>		// 해외유입 수
<qurRate>20.16</qurRate>			// 10만명당 발생률
<seq>1558</seq>				// 게시글번호(국내 시도별 발생현황 고유값)
<stdDay>2020년 04월 10일 00시</stdDay>      // 기준일시
<updateDt>2021-09-16 10:11:33.897</updateDt>	//수정일시분초
*/
