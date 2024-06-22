package org.monkey.demo.wsdl;

import cn.hutool.http.webservice.SoapClient;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.monkey.demo.wsdl.partnerpudo.RegisterParcelContainer;
import org.monkey.demo.wsdl.partnerpudo.RequestData;
import org.monkey.demo.wsdl.partnerpudo.classes.Parcel;
import org.monkey.demo.wsdl.partnerpudo.messages.ParcelContainer;
import org.monkey.demo.wsdl.soap.Body;
import org.monkey.demo.wsdl.soap.Envelope;
import org.monkey.demo.wsdl.soap.Header;
import org.monkey.util.XmlUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class Test {
    public static void main(String[] args) {
        testWsdl();
    }

    private static void testWsdl() {
        String result = "";
        String requestId = UUID.randomUUID().toString();
        Map<String, Object> params = new HashMap<>();
        try {
            String url = "https://tsx.lapker.hu/PudoTest/PartnerPudoService?singleWsdl";
            String methodNameSpace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo";
            String soapAction = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo/IPartnerPudo/RegisterParcelContainer";
            SoapClient soapClient = new SoapClient(url);
            soapClient.setMethod("RegisterParcelContainer", methodNameSpace);
            soapClient.header("SOAPAction", soapAction);
            params = buildCreateParam();
            soapClient.setParams(params);
            Envelope envelope = buildEnvelope();
            String reqXml = XmlUtil.convertToXml(envelope);
            log.info("reqXml:{}", reqXml);

            log.info("请求ID:[{}], url={}, params={}", requestId, url, JSONObject.toJSONString(params));
            //result = soapClient.send();
            log.info("请求ID:[{}], url={}, result={}", requestId, url, result);
        } catch (Exception ex){
            log.error("[请求ID:{}] 出现异常,入参:{},出参:{}.", requestId, JSONObject.toJSONString(params),result,ex);
            throw ex;
        }
    }

    private static Map<String, Object> buildCreateParam() {
        RequestData request = buildRequestData();
        Map<String, Object> params = new HashMap<>();
        params.put("request", request);
        return params;
    }

    private static Envelope buildEnvelope() {
        Envelope envelope = new Envelope();
        envelope.setHeader(buildSoapHeader());
        envelope.setBody(buildSoapBody());
        return envelope;
    }

    private static Header buildSoapHeader() {
        return null;
    }

    private static Body buildSoapBody() {
        Body body = new Body();
        body.setRegisterParcelContainer(buileRegisterParcelContainer());
        return body;
    }

    private static RegisterParcelContainer buileRegisterParcelContainer() {
        RegisterParcelContainer registerParcelContainer = new RegisterParcelContainer();
        registerParcelContainer.setRequest(buildRequestData());
        return registerParcelContainer;
    }

    private static RequestData buildRequestData() {
        RequestData requestData = new RequestData();
        requestData.setArriveDate("");
        requestData.setFinalizeLabelPrinting(false);
        requestData.setOnlyLabelPrinting(false);
        requestData.setParcelContainer(buildParcelContainer());
        requestData.setPartnerAddress(null);
        requestData.setPartnerCode("0000002000");
        requestData.setSupplier("Customer");
        requestData.setSupplimentJSONData(null);
        requestData.setToken(null);
        return requestData;
    }

    private static ParcelContainer buildParcelContainer() {
        ParcelContainer parcelContainer = new ParcelContainer();
        parcelContainer.setParcel(buildParcel());
        return parcelContainer;
    }

    private static Parcel buildParcel() {
        Parcel parcel = new Parcel();
        parcel.setAccountingDeadlineDate("0001-01-01T00:00:00");
        parcel.setAutorizationCode("Groenk2401");
        parcel.setBarCode("202406172144308");
        // Customer
        parcel.setCustomerAddress("Grimmenstein");
        parcel.setCustomerCity("Grimmenstein");
        parcel.setCustomerCode("1104");
        parcel.setCustomerCountryCode("AT");
        parcel.setCustomerEmail("elena @163.com");
        parcel.setCustomerName("ELENA");
        parcel.setCustomerPhone("+36701234567");
        parcel.setCustomerPostalCode("1104");
        parcel.setCustomerRegion("Rayleigh");
        parcel.setCustomerStreetNumber("10 ALLÉE MARYSE HILSZ");
        parcel.setCustomerType("B2C");

        parcel.setDeliveryType("None");
        parcel.setDestinationLocationId(false);
        parcel.setPartnerInvoiced(false);
        // package
        parcel.setPackagePrice(new BigDecimal(15));
        parcel.setPackagePriceCurrency("EUR");
        parcel.setPackageSizeX(new BigDecimal(25));
        parcel.setPackageSizeY(new BigDecimal(10));
        parcel.setPackageSizeZ(new BigDecimal(5));
        parcel.setPackageType("None");
        parcel.setPackageVolume(new BigDecimal(1250));
        parcel.setPackageWeight(new BigDecimal("0.5"));

        parcel.setParcelCount(1);
        parcel.setParcelCreationStatus("Create");
        parcel.setPriceAtDelivery(new BigDecimal("0.0"));
        parcel.setPriceAtDeliveryCurrency("HUF");
        parcel.setReturnOfDocument(false);
        parcel.setServiceType("HomeDeliver");
        parcel.setTracking(false);
        parcel.setTransitTime("2");
        return parcel;
    }
}


























