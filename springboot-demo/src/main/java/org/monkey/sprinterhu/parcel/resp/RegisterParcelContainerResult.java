package org.monkey.sprinterhu.parcel.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * RegisterParcelContainerResult
 *
 * @author cc
 * @since 2024/6/15 18:38
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = {})
public class RegisterParcelContainerResult {

    @XmlAttribute(name = "xmlns:a")
    private String xmlnsA;

    @XmlAttribute(name = "xmlns:i")
    private String xmlnsI;

    @ApiModelProperty("Error code")
    @XmlElement(name = "a:ErrorCode")
    private String errorCode;  // type=String

    @ApiModelProperty("Parcel registration result, information")
    @XmlElement(name = "b:ParcelResult")
    @XmlElementWrapper
    private List<ParcelResult> parcelResults;  // type=List<ParcelResult>
}
