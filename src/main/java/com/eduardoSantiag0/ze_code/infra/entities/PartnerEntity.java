package com.eduardoSantiag0.ze_code.infra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;


@Entity
@Table(name = "partners")
public class PartnerEntity {

  public PartnerEntity() {
  }

  public PartnerEntity(Long id, String tradingName, String ownerName,
      String document, MultiPolygon coverageArea, Point address) {
    this.id = id;
    this.tradingName = tradingName;
    this.ownerName = ownerName;
    this.document = document;
    this.coverageArea = coverageArea;
    this.address = address;
  }

  @Id
  private Long id;

  @Column(name = "trading_name", nullable = false)
  private String tradingName;

  @Column(name = "owner_name", nullable = false)
  private String ownerName;

  @Column(name = "document", nullable = false, unique = true)
  private String document;

  @Column(name = "coverage_area", columnDefinition = "GEOMETRY(MultiPolygon, 4326)", nullable = false)
  private MultiPolygon coverageArea;

  @Column(name = "address", columnDefinition = "GEOMETRY(Point, 4326)", nullable = false)
  private Point address;


  public Long getId() {
    return id;
  }

  public String getTradingName() {
    return tradingName;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public String getDocument() {
    return document;
  }

  public MultiPolygon getCoverageArea() {
    return coverageArea;
  }

  public Point getAddress() {
    return address;
  }
}
