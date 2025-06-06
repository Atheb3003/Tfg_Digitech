package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import lombok.Data;

@Entity
@Table(name = "contact")
@Data
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idContact;

  private String name;
  private String surname;
  private String nif;
  private LocalDate birthDate;
  private String occupation;
  private String country;
  private String province;
  private String town;
  private String direction;
  private String telephoneNumber1;
  private String telephoneNumber2;
  private String email;

  // Nuevo campo para el ID alfanumérico
  private String idContactString;

  @Column(columnDefinition = "TEXT")
  private String observations;

  private Boolean isVisible;

  // Relaciones -------------------------------------
  @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
  private Patient patient;

  @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
  private List<GroupPhotos> groupPhotos;

  // **¡Elimina esta colección!**
  // @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
  // private List<Transaction> transactions;

  // Método público para generar el ID aleatorio
  public String generateRandomId() {
    // Obtener las iniciales del nombre y apellido
    String initials =
        (name != null && !name.isEmpty() ? name.substring(0, 1).toUpperCase() : "")
            + (surname != null && !surname.isEmpty() ? surname.substring(0, 1).toUpperCase() : "");

    // Generar una parte aleatoria de letras y números (por ejemplo, 5 caracteres)
    String randomPart = generateRandomString(5);

    // Formatear el ID como [Iniciales][Parte aleatoria]
    return initials + randomPart;
  }

  // Método para generar una cadena aleatoria de letras y números
  private String generateRandomString(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuilder randomString = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(characters.length());
      randomString.append(characters.charAt(randomIndex));
    }

    return randomString.toString();
  }
}
