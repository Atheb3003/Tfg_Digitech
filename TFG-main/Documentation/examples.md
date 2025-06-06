1. Obtener Detalles de un Contacto

- Solicitud exitosa

GET /contacts/1 HTTP/1.1
Host: api.miapp.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Respuesta Exitosa


HTTP/1.1 200 OK
Content-Type: application/json

{
  "id_contact": 1,
  "name": "María",
  "surname": "González",
  "nif": "12345678Z",
  "birth_date": "1990-05-10",
  "occupation": "Enfermera",
  "country": "España",
  "province": "Madrid",
  "town": "Alcobendas",
  "direction": "Calle Mayor 123",
  "telephone_number_1": "+34 612345678",
  "telephone_number_2": "+34 698765432",
  "email": "maria.gonzalez@example.com",
  "observations": "Paciente con historial alérgico leve.",
  "is_visible": true
}

- Respuesta de Error – Contacto No Encontrado


HTTP/1.1 404 Not Found
Content-Type: application/json

{
  "status": 404,
  "error": "Not Found",
  "message": "No se encontró un contacto con el ID especificado.",
  "path": "/contacts/999"
}


- Respuesta de Error – No Autenticado


HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
  "status": 401,
  "error": "Unauthorized",
  "message": "Authentication is required to access this resource.",
  "path": "/contacts/1"
}


- Respuesta de Error – Fallo Interno

HTTP/1.1 500 Internal Server Error
Content-Type: application/json

{
  "status": 500,
  "error": "Internal Server Error",
  "message": "Ocurrió un error inesperado al obtener los detalles del contacto.",
  "path": "/contacts/1"
}

2. Crear un Contacto

Solicitud: 

POST /contacts HTTP/1.1
Host: api.miapp.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json

{
  "name": "María",
  "surname": "González",
  "nif": "12345678Z",
  "birth_date": "1990-05-10",
  "occupation": "Enfermera",
  "country": "España",
  "province": "Madrid",
  "town": "Alcobendas",
  "direction": "Calle Mayor 123",
  "telephone_number_1": "+34 612345678",
  "telephone_number_2": "+34 698765432",
  "email": "maria.gonzalez@example.com",
  "observations": "Paciente con historial alérgico leve.",
  "is_visible": true
}

- Respuesta Exitosa

HTTP/1.1 201 Created
Content-Type: application/json

{
  "status": "created",
  "data": {
    "id_contact": 1,
    "name": "María",
    "surname": "González",
    "nif": "12345678Z",
    "birth_date": "1990-05-10",
    "occupation": "Enfermera",
    "country": "España",
    "province": "Madrid",
    "town": "Alcobendas",
    "direction": "Calle Mayor 123",
    "telephone_number_1": "+34 612345678",
    "telephone_number_2": "+34 698765432",
    "email": "maria.gonzalez@example.com",
    "observations": "Paciente con historial alérgico leve.",
    "is_visible": true
  }
}
- Respuesta de Error – Datos inválidos

HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "INVALID_INPUT",
    "message": "Los datos osn invalidos."
  }
}


- Respuesta de Error – No Autenticado

HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required to access this resource."
  }
}

- Respuesta de Error – Fallo Interno

HTTP/1.1 500 Internal Server Error
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "INTERNAL_ERROR",
    "message": "Ocurrió un error inesperado al crear el contacto."
  }
}


3. Crear un grupo de fotos (con fotos) para un contacto

Solicitud:
 
POST /contacts/1/photo-groups HTTP/1.1
Authorization: Bearer <token>
Content-Type: application/json

{
  "id_contact": 1,
  "title": "Preoperatorio",
  "description": "Fotos tomadas antes de la cirugía",
  "creation_date": "2025-04-30",
  "is_visible": true,
  "photos": [
    {
      "file_route": "/uploads/preop_1.jpg",
      "is_visible": true
    }
  ]
}

- Respuesta Exitosa:

HTTP/1.1 201 Created
Content-Type: application/json

{
  "status": "created",
  "data": {
    "id_contact": 1,
    "id_group_photos": 10,
    "title": "Preoperatorio",
    "description": "Fotos tomadas antes de la cirugía",
    "creation_date": "2025-04-30",
    "is_visible": true,
    "photos": [
      {
        "id_photo": 101,
        "file_route": "/uploads/preop_1.jpg",
        "is_visible": true
      }
    ]
  }
}

- Error 400 – Datos inválidos

HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "INVALID_INPUT",
    "message": "El campo 'title' es obligatorio."
  }
}


- Error 404 – Contacto no encontrado
http
Copiar
Editar
HTTP/1.1 404 Not Found
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "CONTACT_NOT_FOUND",
    "message": "No se encontró un contacto con ID 999."
  }
}


- Error 401 – No autenticado

HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required to access this resource."
  }
}
- Error 500 – Error interno


HTTP/1.1 500 Internal Server Error
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "INTERNAL_ERROR",
    "message": "Ocurrió un error al crear el grupo de fotos."
  }
}


4.  Eliminar un Grupo de Fotos (con todas sus fotos)

Eliminar un grupo de fotos específico (por ID) y también todas las imágenes asociadas a ese grupo.

Solicitud: 

DELETE /photo-groups/10 HTTP/1.1
Host: api.miapp.com
Authorization: Bearer <token>
Este endpoint requiere autenticación.
El backend debe encargarse de eliminar las fotos asociadas al grupo (cascade delete o en servicio).

Respuesta Exitosa:

HTTP/1.1 200 OK
Content-Type: application/json

{
  "status": "deleted",
  "message": "El grupo de fotos y sus imágenes asociadas fueron eliminadas correctamente.",
  "data": {
    "id_group_photos": 10,
    "id_contact": 1,
    "deleted_photos": [
      {
        "id_photo": 101,
        "file_route": "/uploads/preop_1.jpg"
      },
      {
        "id_photo": 102,
        "file_route": "/uploads/preop_2.jpg"
      }
    ]
  }
}

- Error 404 – Grupo no encontrado

HTTP/1.1 404 Not Found
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "GROUP_NOT_FOUND",
    "message": "No se encontró un grupo de fotos con ID 10."
  }
}

- Error 401 – No autenticado

HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required to access this resource."
  }
}
- Error 500 – Error interno

HTTP/1.1 500 Internal Server Error
Content-Type: application/json

{
  "status": "error",
  "error": {
    "code": "INTERNAL_ERROR",
    "message": "Ocurrió un error al intentar eliminar el grupo de fotos."
  }
}
