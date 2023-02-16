package com.aaa.entily;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.util.Date;

public class FilmInfo {

  private Integer filmId;
  private String filmName;
  private Integer filmType;
  private Integer filmDuration;

  public Integer getFilmId() {
    return filmId;
  }

  public void setFilmId(Integer filmId) {
    this.filmId = filmId;
  }

  public String getFilmName() {
    return filmName;
  }

  public void setFilmName(String filmName) {
    this.filmName = filmName;
  }

  public Integer getFilmType() {
    return filmType;
  }

  public void setFilmType(Integer filmType) {
    this.filmType = filmType;
  }

  public Integer getFilmDuration() {
    return filmDuration;
  }

  public void setFilmDuration(Integer filmDuration) {
    this.filmDuration = filmDuration;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getFilmRemark() {
    return filmRemark;
  }

  public void setFilmRemark(String filmRemark) {
    this.filmRemark = filmRemark;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date releaseDate;
  private String filmRemark;




}
