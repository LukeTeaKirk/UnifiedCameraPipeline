# UnifiedCameraTimestamp
This application acts as a proof of concept to prevent the widespread issue of Mob Lynchings in India. Utilizing state-of-the-art obfuscution 
and SafetyNet Api(s), acts as a unified service to upload and verify the genuine nature of a picture taken - with anti tamper measures in each stage.
This application is secured through usage of WideVine's L1 security chip and decryption algorithm, preventing memory leaks and root bypass.
This application checks for root access, and upon detection deletes the critical parts of the application.
This application is host to 4 components.
1.  The in-app camera viewfinder based on the android Camera2 API
2.  A propietary algoritm to output a uuid for the picture, along with a checksum
3.  Uploads said data packet to a secure server free of cost, with no ability to produce the original picture
4.  Pulls online data packet to crossreference the validity of a recieved photo and verifies the status.
