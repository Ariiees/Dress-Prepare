<div align="center">
  <img src="https://github.com/Ariiees/Dress-Prepare/blob/main/img/logo.jpg" alt="logo" width="100">
</div>

# VASTRAM: An Android APP for Dress Prepare
Are you struggling with what to wear everday? Are you worried about unexpected weather conditions? Are you not sure about what to wear for some special dress code?

**VASTRAM** will give you detailed weather information based on you current location, and will provide you personalized dress suggestions using AI-Driving. You could also check different dress code dressing examples!

PS: This is an Android APP Prototype for **Udel CISC 482/682** Final Project.

## Functions
- Task1: AI Chat ([chat demo](https://drive.google.com/file/d/13u_paQ2dCHPHIFcuEfFXeZOV5EB67CHC/view?usp=sharing) and [regenerate demo](https://drive.google.com/file/d/11rUJEj7s40ZoPl0h_PV7rPZWzv2HQYr_/view?usp=sharing))

  Will provide dressing sugesstion according to today's weather conditions, each suggestion could be visualized through **Visualize** Button, and if you choose to apply, it will shown and update your home screen avatar. You could also regenerate another suggestion using **Regenerate** button
  
- Task2: Weather  ([demo](https://drive.google.com/file/d/11L2rLYuup-yPcErRbR-qraAgQ5rgEoPJ/view?usp=sharing))
  
  Will give you detailed 24h weather condiont based on you current location, which including temperature, humidities, wind directions ect. The temerature and feels like temerature will automatically updated to your home screen.
  
- Task3: Dress Code  ([demo](https://drive.google.com/file/d/11rUJEj7s40ZoPl0h_PV7rPZWzv2HQYr_/view?usp=sharing))
  
  Will give you dressing suggestions for differnt type of dress code, you could directly visualize them and get detailed information if you click **More Inforamtion** Button.

- Accessiblity: Screen Reader  ([demo](https://drive.google.com/file/d/11L2rLYuup-yPcErRbR-qraAgQ5rgEoPJ/view?usp=sharing))
  
- Other: Home
  
  Click location icon to retrieve latest GPS data for location and weather information update.
  Shown temerature is linked with Weather page and will update automatically.
  Shown avatar could be updated with AI Chat page if you choose to appy that suggestion.
  
- Other: Setting

  Logout through profile setting page.

## File Structure: 
- MainActivity -> for loading screen and jump to Home
- Home -> Code for Home Page
- Chat -> Code for AI Chat task
- DressCode -> Code for Dress Code task
- Weather -> Code to Weather task
- Setting -> Code for profile and seeting
- Util -> Code for all utility function that used to link differnt page information.
- LocationHelper -> Code for GPS Data getting, permission granted and location text view updates.

## How to Use
1. ```git clone https://github.com/Ariiees/Dress-Prepare.git```
2. Open Dress-Prepare with Android Studio
3. File -> Sync Project with Gradle Files
4. Run simulator with Medium Phone API 35

## Figma Design
Our figma prototype shown [here](https://www.figma.com/proto/rbjHhWBnqQBYRo0ghO191P/Dress-Prepare-team-library?node-id=3353-1150&starting-point-node-id=3318%3A3499&t=NlOzoQFdFFG42OKP-1)

## Video
Check our final Video [here](https://drive.google.com/file/d/1usHHalKx1eaGP5_iDqU0mag9x3pWFcO7/view?usp=sharing)

## Reference
[1][GPS Location](https://developers.google.com/android/reference/com/google/android/gms/location/package-summary)

[2][AlertDialog](https://developer.android.com/reference/android/app/AlertDialog)

[3][Weather API](https://open-meteo.com)

[4][Chart library](https://github.com/PhilJay/MPAndroidChart/tree/master)

[5][Dress code](https://www.paperlesspost.com/blog/the-ultimate-guide-to-wedding-dress-codes-and-guest-attire/#)
