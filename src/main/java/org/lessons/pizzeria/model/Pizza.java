package org.lessons.pizzeria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizza")
public class Pizza {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Name cannot be null")
  @NotBlank(message = "Name cannot be empty")
  @Size(max = 255, message = "Name too long, max 255 character")
  private String name;

  @Size(max = 255, message = "Name too long, max 255 character")
  private String description;

  @Column(name = "photo", nullable = false)
  @NotNull(message = "Photo URL cannot be null")
  @NotBlank(message = "Photo URL cannot empty")
  private String photoUrl;

  @Column(precision = 2)
  @NotNull(message = "Price cannot be null")
  @PositiveOrZero(message = "Price must be greater or equal than 0")
  private Double price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "pizza [id=" + id + ", name=" + name + ", description=" + description + ", photoUrl=" + photoUrl + ", price="
        + price + "]";
  }

}
