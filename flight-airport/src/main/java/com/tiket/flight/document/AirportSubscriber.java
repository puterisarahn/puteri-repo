package com.tiket.flight.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class AirportSubscriber {

	@Id
	public ObjectId _id;
	public String airportName;
	public String airportCode;
	public String cityCode;
	public Boolean isActive;
	
	 public AirportSubscriber() {
		    super();
	 }
	
	 public AirportSubscriber(ObjectId _id, String airportName, String airportCode, String cityCode, Boolean isActive) {
	    this._id = _id;
	    this.airportName = airportName;
	    this.airportCode = airportCode;
	    this.cityCode = cityCode;
	    this.isActive = isActive;
	  }
	
	
	
	public String get_id() {
		return _id.toHexString();
	}
	public void set_id(ObjectId id) {
		this._id = id;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	  public String toString() {
	    return "AirportSubscriber [airportName=" + airportName + ", airportCode=" + airportCode + ", cityCode=" + cityCode + ", isActive=" + isActive +"]";
	  }
}
