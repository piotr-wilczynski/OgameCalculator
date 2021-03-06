/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package api;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "number",
    "language",
    "timezone",
    "domain",
    "version",
    "speed",
    "speedFleet",
    "galaxies",
    "systems",
    "acs",
    "rapidFire",
    "defToTF",
    "debrisFactor",
    "repairFactor",
    "newbieProtectionLimit",
    "newbieProtectionHigh",
    "topScore",
    "bonusFields",
    "donutGalaxy",
    "donutSystem"
})
@XmlRootElement(name = "serverData")
public class ServerData implements Timestamp{

    protected String name;
    @XmlElement(required = true)
    protected String number;
    @XmlElement(required = true)
    protected String language;
    @XmlElement(required = true)
    protected String timezone;
    @XmlElement(required = true)
    protected String domain;
    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected BigInteger speed;
    @XmlElement(required = true)
    protected BigInteger speedFleet;
    @XmlElement(required = true)
    protected BigInteger galaxies;
    @XmlElement(required = true)
    protected BigInteger systems;
    protected boolean acs;
    protected boolean rapidFire;
    protected boolean defToTF;
    protected float debrisFactor;
    protected float repairFactor;
    protected int newbieProtectionLimit;
    protected int newbieProtectionHigh;
    protected float topScore;
    protected int bonusFields;
    protected boolean donutGalaxy;
    protected boolean donutSystem;
    @XmlAttribute(name = "timestamp")
    protected BigInteger timestamp;
    @XmlAttribute(name = "serverId")
    protected String serverId;

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
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the speed property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSpeed() {
        return speed;
    }

    /**
     * Sets the value of the speed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSpeed(BigInteger value) {
        this.speed = value;
    }

    /**
     * Gets the value of the speedFleet property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSpeedFleet() {
        return speedFleet;
    }

    /**
     * Sets the value of the speedFleet property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSpeedFleet(BigInteger value) {
        this.speedFleet = value;
    }

    /**
     * Gets the value of the galaxies property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGalaxies() {
        return galaxies;
    }

    /**
     * Sets the value of the galaxies property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGalaxies(BigInteger value) {
        this.galaxies = value;
    }

    /**
     * Gets the value of the systems property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSystems() {
        return systems;
    }

    /**
     * Sets the value of the systems property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSystems(BigInteger value) {
        this.systems = value;
    }

    public boolean isAcs() {
        return acs;
    }

    public void setAcs(boolean value) {
        this.acs = value;
    }

    public boolean isRapidFire() {
        return rapidFire;
    }

    public void setRapidFire(boolean value) {
        this.rapidFire = value;
    }
    
    public boolean isDefToTF() {
        return defToTF;
    }

    public void setDefToTF(boolean value) {
        this.defToTF = value;
    }

    public float getDebrisFactor() {
        return debrisFactor;
    }

    public void setDebrisFactor(float value) {
        this.debrisFactor = value;
    }

    public float getRepairFactor() {
        return repairFactor;
    }

    public void setRepairFactor(float value) {
        this.repairFactor = value;
    }

    public int getNewbieProtectionLimit() {
        return newbieProtectionLimit;
    }

    public void setNewbieProtectionLimit(int value) {
        this.newbieProtectionLimit = value;
    }

    public int getNewbieProtectionHigh() {
        return newbieProtectionHigh;
    }

    public void setNewbieProtectionHigh(int value) {
        this.newbieProtectionHigh = value;
    }

    public float getTopScore() {
        return topScore;
    }

    public void setTopScore(float value) {
        this.topScore = value;
    }

    public int getBonusFields() {
        return bonusFields;
    }

    public void setBonusFields(int value) {
        this.bonusFields = value;
    }

    public boolean isDonutGalaxy() {
        return donutGalaxy;
    }

    public void setDonutGalaxy(boolean value) {
        this.donutGalaxy = value;
    }

    public boolean isDonutSystem() {
        return donutSystem;
    }

    public void setDonutSystem(boolean value) {
        this.donutSystem = value;
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

}
