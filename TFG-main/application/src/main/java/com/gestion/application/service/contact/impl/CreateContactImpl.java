package com.gestion.application.service.contact.impl;

import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateContactImpl {

  private final ContactRepository contactRepository;

  public Contact crearContacto(Contact contacto) {
    // Generar y asignar el idContactString antes de guardar el contacto
    if (contacto.getIdContactString() == null) {
      // Llamar al método para generar el idContactString
      String generatedId = generateRandomId(contacto);

      // Asignar el ID generado al campo idContactString
      contacto.setIdContactString(generatedId);
    }

    // Guardamos el contacto con el idContactString generado
    return contactRepository.save(contacto);
  }

  // Método para generar el ID aleatorio basado en las iniciales y una parte aleatoria
  private String generateRandomId(Contact contacto) {
    // Obtener las iniciales del nombre y apellido
    String initials =
        (contacto.getName() != null && !contacto.getName().isEmpty()
                ? contacto.getName().substring(0, 1).toUpperCase()
                : "")
            + (contacto.getSurname() != null && !contacto.getSurname().isEmpty()
                ? contacto.getSurname().substring(0, 1).toUpperCase()
                : "");

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
