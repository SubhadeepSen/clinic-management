# Clinic Management System

---

## System Requirements
### Functional
- Only authenticated user should get access to the system
- Single client application and should seamlessly run in desktop
- User should be be able to create new patient record with the required information. The information contains patient personal inforamtion as well as current diagnostic information.
- There should be an option to populate patient personal information by providing phone number if patient personal information already exists while creating a new patient record
- User should be able to enter invoice related information like work done and amount charged in an appointment
- System should generate __PDF Prescription as well as Invoice__ upon confirmation of processing the record
- User should be able to search any existing patient information and re-generate the Prescription as well as the Invoice as needed
- There should be an option to search today's appointments

### Non-Functional
- Single user
- Should run in desktop local server
- One click application start up and one click application shut down

###### **_Note:_** System does not provide any option to create any user account or reset password if forgotten.

---

## System Architecture

<div style="text-align:center"><img src="https://github.com/SubhadeepSen/clinic-management/blob/main/documents/architecture.jpg"/></div>

---

## Data Model
### User Account Document

```json
{
"id": "userId",
"username": "encryptedUserName",
"password": "encryptedPassword"
}
```

* index at **_username_**

### Patient Info Document

```json
{
"id": "patientId",
"personalInfo": {
    "fullName": "patientFullName",
    "age": "patientAge",
    "gender": "patientGender",
    "address": "patientFullAddress",
    "phoneNumber": "patientPhoneNumber",
    "emailId": "patientEmailId",
    "fullName": "patientFullName",
    "occupation": "patientOccupation",
    "timestamp": "createdAtTimestamp",
},
"consultations": [
    {
        "dateOfVisit": "patientDateOfVisit",
        "chiefComplaints": ["complaints_1", "complaints_2"],
        "onExaminations": ["examination_1", "examination_2"],
        "medicalHistories": ["history_1", "history_2"],
        "investigations": ["investigation_1", "investigation_2"],
        "advices": ["advice_1", "advice_2"],
        "workDones": ["workDone_1", "workDone_2"],
        "medicines": ["medicines_1", "medicines_2"],
        "nextAppointmentDate": "nextAppointmentDate",
        "invoice": {
            "invoiceId": "invoiceId",
            "workDoneAmounts": [{
                "workDone": "workDone",
                "amount": "amountCharged",
            }],
        },
        "timestamp": "createdAtTimestamp"
    }
]
}
```

* index at **_patientId_**, **_phoneNumber_** and **_invoiceId_**

---

## Tech Stack
### Database (Document Based)
- Mongo DB

### Backend (Java 8)
- Springboot (to create an executable archieve with all resources and with embedded tomcat)

### Front End
- HTML, CSS, Javascript, JQuery, Bootstrap v3

---

## UI Layouts
INP

---

## Setup & Deployment
INP

---