package org.monkey.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.monkey.dto.sprinterhu.parcel.Parcel;
import org.monkey.dto.sprinterhu.parcel.RegisterParcelContainerReq;
import org.monkey.dto.sprinterhu.parcel.enums.*;
import org.monkey.dto.sprinterhu.test.Person;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DpdatController
 *
 * @author cc
 * @since 2024/6/13 9:22
 */
@RestController
@RequestMapping("/sprinterhu/parcel")
@Slf4j
public class SprinterhuController {

    /**
     * sprinterhu下单
     * @return
     */
    @GetMapping("/create")
    public String create() throws ParseException {

        log.info("receive req: /sprinterhu/parcel/create");
        String url = "https://tsx.lapker.hu/PudoTest/PartnerPudoService?singleWsdl";

        RegisterParcelContainerReq req = buileParcelContainerReq();
        log.info("调用[Sprinterhu]下单接口, url={}", url);
        log.info("调用[Sprinterhu]下单接口, 请求参数={}", req);
        try (HttpResponse response = HttpRequest.get(url)
                .contentType("application/xml; charset=utf-8")
                .body(req.toString())
                .timeout(50000).execute();) {
            String body = response.body();
            log.info("调用[Sprinterhu]下单接口, 响应结果={}", body);
            return body;
        } catch (Exception e) {
            log.info("异常：", e);
            return e.getMessage();
        }
    }

    @PostMapping(value = "/xml", produces = "application/xml")
    public String testXml(@RequestBody Person person) {
        log.info("receive req: /sprinterhu/parcel/xml");
        log.info("请求参数：{}", person);
        return "success";
    }

    private static RegisterParcelContainerReq buileParcelContainerReq() throws ParseException {
        RegisterParcelContainerReq req = new RegisterParcelContainerReq();
        req.setFinalizeLabelPrinting(false);
        req.setOnlyLabelPrinting(false);
        Parcel parcel = new Parcel();
        Date accountingDeadlineDate = DateUtils.parseDate("0001-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        parcel.setAccountingDeadlineDate(accountingDeadlineDate);
        parcel.setAutorizationCode("Groenk2401");
        parcel.setBarCode("TEST2024060300364");
        //parcel.setContactName("");
        parcel.setCustomerAddress("Mádi utca");
        parcel.setCustomerCity("Budapest");
        parcel.setCustomerCode("888");
        parcel.setCustomerCountryCode("HU");
        parcel.setCustomerEmail("teszt@email.hu");
        parcel.setCustomerName("Kiss Péter ");
        parcel.setCustomerPhone("+36701234567");
        parcel.setCustomerPostalCode("1104");
        //parcel.setCustomerRegion("");
        parcel.setCustomerStreetNumber("102");
        parcel.setCustomerType(CustomerTypeEnum.B2C);
        parcel.setDeliveryPrice(new BigDecimal("0.0"));
        //parcel.setDeliveryPriceCurrency("");
        parcel.setDeliveryType(ParcelDeliveryTypeEnum.NONE);
        //parcel.setDescription("");
        //parcel.setDestinationLocationId("");
        //parcel.setDirectPackageBarCode("");
        //parcel.setInvoiceNumber("");
        parcel.setPartnerInvoiced(false);
        parcel.setPackagePrice(new BigDecimal("0.0"));
        parcel.setPackagePriceCurrency("HUF");
        parcel.setPackageSizeX(new BigDecimal("30.00"));
        parcel.setPackageSizeY(new BigDecimal("40.00"));
        parcel.setPackageSizeZ(new BigDecimal("30.00"));
        parcel.setPackageType(PackageTypeEnum.NONE);
        parcel.setPackageVolume(new BigDecimal("0.036"));
        parcel.setPackageWeight(new BigDecimal("18.00"));
        //parcel.setParcelContentDescription1("");
        parcel.setParcelCount(1);
        parcel.setParcelCreationStatus(ParcelCreationStatusEnum.CREATE);
        //parcel.setPickupCustomerName("");
        //parcel.setPickupLocationId("");
        //parcel.setPickupNotificationEmailAddress("");
        //parcel.setPickupNotificationPhone("");
        parcel.setPriceAtDelivery(new BigDecimal("0.0"));
        parcel.setPriceAtDeliveryCurrency("HUF");
        //parcel.setReferenceBarCode("");
        Date registerDate = DateUtils.parseDate("2024-01-30 18:16:01", "yyyy-MM-dd HH:mm:SS");
        parcel.setRegisterDate(registerDate);
        parcel.setReturnOfDocument(false);
        parcel.setServiceType(ParcelServiceTypeEnum.HOME_DELIVER);
        //parcel.setSuplimentJsonData("");
        //parcel.setThirdPersonDelivery("");
        parcel.setTracking(false);
        parcel.setTransitTime(2);

        List<Parcel> parcelContainer = new ArrayList<>();
        parcelContainer.add(parcel);
        req.setParcelContainer(parcelContainer);
        //req.setPartnerAddress("");
        req.setPartnerCode("0000002000");
        req.setSupplier(SupplierEnum.CUSTOMER);
        //req.setSuplimentJsonData("");
        req.setToken("");
        return req;
    }
}
