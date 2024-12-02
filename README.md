# Dress-Prepare Dress Code Page
This branch contains the code for Dress Code Page, current mainfest is set to MainActivity.

- [x] Drop Down List
- [x] More Info Button 
- [x] Woman Summer Dress Tie
- [ ] Man Summer Dress Tie (need to combine with setting gender data)
- [ ] Setting Page


## Quick Start on Implementation
Go Dress-Prepare/app/src/main/res/drawable to find/add png component
Go Dress-Prepare/app/src/main/res/layout to add/modify new/existing pages
Go Dress-Prepare/app/src/main/java/com/example/dresscode to find all our Java class code framwork
Notice: when you trying to add a new java class, please go Dress-Prepare/app/src/main and register a new activity in AndoirdManifest.xml
otherwise when you trying to enter your new class the code will break.

The whole project structure is: 
- MainActivity -> for loading screen and jump to Home
- Util -> for all utility function or code (please write as **public static** function for usage
- DressCode -> dresscoe page that offer drop down list
- Home -> WIP Home page that show basic idea of app
- Weather -> WIP should be weather page
- Chat -> WIP should be ai chating
- Setting.java -> WIP should be profile page

### We need a camera button that can let user to take a photo of their cloth. Leave a MSG below for ideas.
