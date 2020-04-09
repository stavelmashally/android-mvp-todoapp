# Android MVP todoapp
Android App built using MVP architecture, Dagger, RxJava and Retrofit.    
API : https://github.com/stavelmashally/node-express-mongoose-todo-api
<br><br>
<p align="center">
  <img src="https://raw.githubusercontent.com/stavelmashally/android-mvp-todoapp/master/screenshots/login.png" width="200">
  <img src="https://raw.githubusercontent.com/stavelmashally/android-mvp-todoapp/master/screenshots/register.png" width="200">
  <img src="https://raw.githubusercontent.com/stavelmashally/android-mvp-todoapp/master/screenshots/list.png" width="200">
  <img src="https://raw.githubusercontent.com/stavelmashally/android-mvp-todoapp/master/screenshots/save.png" width="200">
</p>
<br>

## Project structure
### app structure contains 4 packages:

**data**: Implementaion of the Repository pattern, contains all the data accessing.          
**di**: Contains the classes that provide dependencies.         
**presentaion**: MVP structure, all Activities, Fragments and UI elements in this package.          
**utils**: Utility classes.     

## Tools
* Dagger 2
* RxJava 2
* Retrofit 2
* ButterKnife

## Running

1. Run the Node.js [API](https://github.com/stavelmashally/node-express-mongoose-todo-api).
2. Change the base url in the Constants class to the api url. 
