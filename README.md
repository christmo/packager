packager
========

Framework basado en el concepto de paquetes de información, manejando una única estructura flexible, configurable y personalizable en formato JSON, encargado de transportar la información desde un repositorio de datos (MongoDB, MySQL, PostgresDB, etc) hacia un front end desarrollado en cualquier tecnología, combinado con otras librerías se puede reemplazar el uso de jsp o jsf y manejando una interfaz más limpia y pura en html5.


Estructura del mensaje base (Aún en desarrollo):
<pre><code>
{
  "header": {
    "idUser": "1",
    "mode": "GET",
    "actionType": "DETAIL",
    "objectType": "ITEM"
  },
  "packetData": [
    {
      "id": "51bd70de5a65347b795d5ccc",
      "database": "mongo",
      "collection": "test",
      "request": {
        "valor": 126
      },
      "response": [
        {
          "rowId": "0",
          "columns": [
            {
              "key": "0",
              "value": {
                "valor": 126
              }
            },
            {
              "key": "7",
              "value": {
                "valor": 126
              }
            }
          ]
        }
      ]
    }
  ],
  "error": [
    {
      "errorCode": 500,
      "description": "Se me hizo pedazos la bici!!! Esto está listo... :-)",
      "errorType": "ERROR",
      "parameters": {
        "2": "Esto está listo... :-)",
        "1": "pedazos"
      }
    }
  ]
}
</code></pre>

Todo este proyecto está bajo desarrollo si quieres unirte te esperamos, ha sido iniciado por:

Christian Mora<br/>
Santiago Plascencia
