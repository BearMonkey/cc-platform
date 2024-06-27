package org.monkey.demo.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * TestController
 *
 * @author cc
 * @since 2024/6/26 15:23
 */
@RestController("/security")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public Result<String> test() {
        log.info("Receive request, /security/test");
        BigDecimal bigDecimal = new BigDecimal(1);

        return Result.success();
    }


}
/*
billNo
billOrderNo
billSenderDate
billDispatchTime
sndArea
sndSite
sndCusId
sndCusName
sndCusPerson
sndCusComp
sndCusPhone
sndCusTel
sndCusAddr
dstArea
dstSite
dstCusId
dstCusName
dstCusPerson
dstCusComp
dstCusPhone
dstCusTel
dstCusAddr
goodsType
goodsName
goodsPackType
goodsl
goodsw
goodsh
goodsPcs
goodsWeight
goodsVolume
goodsWeightBalance
goodsWeightCharged
typeProduct
typeService
typeBackBill
typeControlGoods
typeDirect
timeinAdvance
flightDate
flightNumber
flightFeeRate
flightFeeWay
flightFeeGround
flightFeeInsure
payMethod
payType
chargeMode
feeUnitPrice
feeWay
feeBackBill
feeDispatch
feeUpstairs
feeInstore
feeOverWeight
feeOverLength
feeUnloadFee
feeCost
feeStore
feeGoodsControl
feePack
feePickup
feeOilExtra
feeForGoods
feeForGoodsPaytp
feeForGoodsRate
feeForGoodsHander
accName
accNumber
accBankName
accReceiverPhone
feeInsure
feeInsureRate
feeInsurePay
feeMsg
feeOthers
feeCommType
feeCommAmount
feeTotal
feePayNow
feePayArrive
feePayMonth
despBill
despSystem
inputSite
inputEmp
inputTime
inputType
backBillNo
inputTypeOrder
fcNo
fcModifyType
fcModifyPerson
fcModifySite
fcModifyTime
fcAuditStatus
fcManAuditPerson
fcManAuditSite
fcManAuditTime
fcFinAuditPerson
fcFinAuditSite
fcFinAuditTime
cancelStatus
cancelPerson
cancelSite
cancelReason
cancelTime
fcModifyDesp
routingNo
feeLower
pickupEmp
statMsg
statOpenBill
sndProvince
sndCity
sndRegion
dstProvince
dstCity
dstRegions
appendStatus
fcStoAuditPerson
fcStoAuditSite
fcStoAuditTime
fcStoSite
insideWayFee
sendCarType
cusBillNo
feeBillChange
labelList
fefWayFee
backCommType
lineType
goodsLine
vehicleNo
vehicleFee
fcCancelReason
feePayReceipt
payMonthArrive
feeForGoodsPickAddr
discountSource
conversionType
kaFlag
feeOthersType
reasonFree
oldBillNo
feeTransInternation
freightMaori
prePaidFreight
recModel
delModel
realNameFlag
cardId
feeDispatchSt
feePickupSt
dispatchLatelySite
accDistance
accOpenbank
recDistance
delDistance
sndSiteConfirm
dstSiteConfirm
fcSndAuditPerson
fcSndAuditSite
fcSndAuditTime
finSiteConfirm
goodsNodetId
billNo
goodsName
goodsType
goodsPcs
goodsUnitWeight
goodsTotalWeight
goodsl
goodsw
goodsh
goodsVolume
goodsWayFee
fcNo
fcDetNo
fcModifyType
fcModifyDesp
goodsNo2



*/
