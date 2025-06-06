package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ContactResponse {

  @JsonProperty("id_contact")
  private Integer idContact;

  private String name;
  private String surname;
  private String nif;

  @JsonProperty("birth_date")
  private LocalDate birthDate;

  private String occupation;
  private String country;
  private String province;
  private String town;
  private String direction;

  @JsonProperty("telephone_number_1")
  private String telephoneNumber1;

  @JsonProperty("telephone_number_2")
  private String telephoneNumber2;

  private String email;
  private String observations;

  @JsonProperty("is_visible")
  private Boolean isVisible;
}
