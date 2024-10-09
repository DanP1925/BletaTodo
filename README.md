# BletaTodo

## Acerca del proyecto



## Arquitectura

Se ha divido el proyecto en 3 capas mediante paquetes:

- Presentation: En este capa se encuentra la interfaz de usuario mediante Composables y se utiliza los ViewModel como contendores de estado
- Domain: En esta capa se encuentran los casos de uso que abstraen la lógica de negocio y las interfaces de los repositorios.
- Data: En esta capa se encuentran los repositorios . En el caso de este proyecto solo se está utilizando como dataSource de una base de datos local.

La finalidad de esta división es que tanto la capa Presentation como Data dependan solamente de Domain y asi tenerlas desacopladas entre si.



## Librerías utilizadas

- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android): Para hacer inyección de dependencias
- [Navigation](https://developer.android.com/develop/ui/compose/navigation): Para manejar la navegación entre las pantallas
- [Room](https://developer.android.com/training/data-storage/room): Para manejar la interacción con la base de datos
- [MockK](https://mockk.io/): Para crear mocks en las pruebas unitarias y pruebas instrumentadas

## Mejoras a futuro

- Agregar pruebas de integracion entre panatallas
- Agregar un DataSource que interactúe con un servidor
