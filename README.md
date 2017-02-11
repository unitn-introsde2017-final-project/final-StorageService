# Storage Service for the Final Project at IntroSDE

## API Endpoints

### /StorageService/UserProfile?userId={userId} [GET, POST]

Calls the REST API endpoint of the LocalDatabase [PROTOCOL] /sdelab/person/{userId} and returns the result
Details of the call can be found at the Local Database description

### /StorageService/WeatherInformation?city={cityId} [GET]

Calls the SOAP API endpoint of the WeatherAdapter [PROTOCOL] getWeatherInformationByCity({cityId}) and returns the result
Details of the call can be found at the Weather Adapter description

### /StorageService/addUpdateStepCount [PUT]

Calls the REST API endpoint of the LocalDatabase [PROTOCOL] /sdelab/steps/{userId} and returns the result
Details of the call can be found at the Local Database description

## Copyright

&copy; Sándor Tibor Nagy as the final project for Introduction to Service Design and Engineering course 2016/2017 at [UNITN](http://www.unitn.it/)