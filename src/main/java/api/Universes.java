/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package api;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "universe"
})
@XmlRootElement(name = "universes")
public class Universes implements Timestamp{

    @XmlElement(required = true)
    protected List<Universes.Universe> universe;
    @XmlAttribute(name = "timestamp")
    protected Integer timestamp;
    @XmlAttribute(name = "serverId")
    protected String serverId;

    public List<Universes.Universe> getUniverse() {
        if (universe == null) {
            universe = new ArrayList<Universes.Universe>();
        }
        return this.universe;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public BigInteger getTimestamp() {
        return new BigInteger(""+timestamp);
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimestamp(Integer value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the serverId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * Sets the value of the serverId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerId(String value) {
        this.serverId = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Universe {

        @XmlAttribute(name = "id", required = true)
        protected String id;
        @XmlAttribute(name = "href", required = true)
        protected String href;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the href property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHref() {
            return href;
        }

        /**
         * Sets the value of the href property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHref(String value) {
            this.href = value;
        }

    }

}
