/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package api;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "planet"
})
@XmlRootElement(name = "universe")
public class Universe implements Timestamp{

    @XmlElement(required = true)
    protected Universe.Planet planet;
    @XmlAttribute(name = "timestamp")
    protected BigInteger timestamp;
    @XmlAttribute(name = "serverId")
    protected String serverId;

    /**
     * Gets the value of the planet property.
     * 
     * @return
     *     possible object is
     *     {@link Universe.Planet }
     *     
     */
    public Universe.Planet getPlanet() {
        return planet;
    }

    /**
     * Sets the value of the planet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Universe.Planet }
     *     
     */
    public void setPlanet(Universe.Planet value) {
        this.planet = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimestamp(BigInteger value) {
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
    @XmlType(name = "", propOrder = {
        "moon"
    })
    public static class Planet {

        protected Universe.Planet.Moon moon;
        @XmlAttribute(name = "id", required = true)
        protected BigInteger id;
        @XmlAttribute(name = "player", required = true)
        protected BigInteger player;
        @XmlAttribute(name = "name", required = true)
        protected String name;
        @XmlAttribute(name = "coords", required = true)
        protected String coords;

        /**
         * Gets the value of the moon property.
         * 
         * @return
         *     possible object is
         *     {@link Universe.Planet.Moon }
         *     
         */
        public Universe.Planet.Moon getMoon() {
            return moon;
        }

        /**
         * Sets the value of the moon property.
         * 
         * @param value
         *     allowed object is
         *     {@link Universe.Planet.Moon }
         *     
         */
        public void setMoon(Universe.Planet.Moon value) {
            this.moon = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setId(BigInteger value) {
            this.id = value;
        }

        /**
         * Gets the value of the player property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getPlayer() {
            return player;
        }

        /**
         * Sets the value of the player property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setPlayer(BigInteger value) {
            this.player = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the coords property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCoords() {
            return coords;
        }

        /**
         * Sets the value of the coords property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCoords(String value) {
            this.coords = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Moon {

            @XmlAttribute(name = "id", required = true)
            protected BigInteger id;
            @XmlAttribute(name = "name", required = true)
            protected BigInteger name;
            @XmlAttribute(name = "size", required = true)
            protected BigInteger size;

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setId(BigInteger value) {
                this.id = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setName(BigInteger value) {
                this.name = value;
            }

            /**
             * Gets the value of the size property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getSize() {
                return size;
            }

            /**
             * Sets the value of the size property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setSize(BigInteger value) {
                this.size = value;
            }

        }

    }

}
