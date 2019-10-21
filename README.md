# UnifiedCameraTimestamp
This application acts as a proof of concept to prevent the widespread issue of Mob Lynchings in India. Utilizing state-of-the-art obfuscution and SafetyNet Api(s), this acts as a unified service to upload and verify the genuine nature of a picture taken - with anti tamper measures present in each stage.
This application is secured through usage of WideVine's L1 security chip and decryption algorithm, preventing memory leaks and root bypass.
This application checks for root access, and upon detection disables the critical boot parts of the application.
This application is host to 4 components.
1.  The in-app camera viewfinder based on the android Camera2 API;
2.  A propietary algoritm to output a uuid for the picture, along with a checksum;
3.  Uploads said data packet to a secure server free of cost, with no ability to produce the original picture;
4.  Pulls online data packet to crossreference the validity of a recieved photo and verifies the status.;

User Side Timeline:
1. User clicks picture from app;
2. Uploads picture uuid and a proprietary checksum (based on the color of individual blocks of the bitmap) from the Output activity;
3. Copies reference id (+ link) for purpose of sharing with other people(2nd party), along with original image.;
4. 2nd party downloads app from link, pastes reference id in the Verify Activity;
5. Based on status of photo genuinity, commits or chooses not to commit certain illegal actions;
6. Profit;

Use Case:
Preventing the widespread of doctored pictures/videos of mob lyncing inducing activities;
Digital notary without having to divulge private information;
Alternative no cost scenerio when backend in based on a blockchain;
