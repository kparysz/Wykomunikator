# Wykomunikator
Comminicator for wykop.pl

## What is this?

This application is for www.wykop.pl users. App allows to send private message between users. 
API's wykop provides data to app. Wykomunikator does not have UI :( 
If you want to create some UT for app please write to me.

## What I use
+ Kotlin
+ RxJava2/RxAndroid2
+ Retrofit2
+ Dagger2
+ Mockito2
+ Espresso 2.2
+ MockitoKotlin

## How to join
1. Download project
2. If you want to test app for real data you should have access to wykop[API](https://www.wykop.pl/zaloguj/?fEr[0]=ZmM4NWh0dHBzOi8vd3d3Lnd5a29wLnBsL2RsYS1wcm9ncmFtaXN0b3cvbm93YS1hcGxpa2FjamEv)
3. You should create fake jks file and put _key_ folder next to _app_ folder
4. You should create _jks.properties_ file in _key_ folder with such info:
```groovy
STORE_FILE=/path/to/keystore
STORE_PASSWORD=password
KEY_ALIAS=alias
KEY_PASSWORD=password
```
5. Create class WykopConfig _/src/main/kotlin/pl/kparysz/wykomessages/network_ and put:
```kotlin
val REGISTER_APP_LINK = "http://a.wykop.pl/user/connect/appkey,"
val API_APP_KEY = "YOUR_APP_KEY"
val API_APP_SECRET_KEY = "_YOUR_SECRET_KEY"
```
6. Make changes
7. Write unit tests if needed
8. Write automated tests if needed
9. Make pull request
10. BE HAPPY

## How to run automated tests
1. Change build variant from _release/debug_ to _automated_

License
=======

    Copyright 2013 Square, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.