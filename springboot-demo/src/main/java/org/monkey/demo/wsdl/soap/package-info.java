@XmlSchema(
        namespace = "http://schemas.xmlsoap.org/soap/envelope/",
        xmlns = {
            @XmlNs(prefix = "soapenv", namespaceURI = "http://schemas.xmlsoap.org/soap/envelope/")
        }
)
package org.monkey.demo.wsdl.soap;
/**
 * package-info
 *
 * @author Monkey
 * @since 2024/6/22
 */

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;