# Project Plan

**Author**: Shmuel Halbfinger (Project Manager)

## 1 Introduction

Our product is called *Grocery List Manager*. It will be a manager of lists of grocery items. Users can create and edit lists of items organized by item type, changing their quantity and unit type. Users can also check off (or uncheck) items to keep track of what items have been acquired.

## 2 Process Description

The steps of the software development lifecycle were utilized in the creation of our project. We gathered requirements, analyzed them, created designs modeling the requirements, and went through implementation and testing. Each of these phases is described below.

##### Requirement Gathering and Analysis

During this phase, we collected the requirements from the stakeholders of the proposed application. We then analyzed the requirements in order to fully understand how the application should function. 

##### Design

During this phase, we used our analysis of the requirements to design a project outline. Requirements were further clarified, and diagrams and documentation were created to elaborate on the requirements and create a project architecture which could be implemented during development

##### Implementation

During this phase, we used the architecture and documentation developed during the Design phase to implement the application. This phase included the actual software programming. During this phase, use cases, classes, and components were actualized, and documentation was edited if need be

##### Testing

During this phase, application functionality is testing using different tests, such as unit tests and system-level tests. Bugs were fixed, and functionality was tested and retested to confirm that the application functioned as intended and denoted in the requirements. These tests are elaborated upon in the Test Plan document



##### Summary

All of these phases were iterative, meaning that in any given phase, another phase could be revisited in order to clarify on any misunderstandings.

Throughout these phases, team meetings took place to make sure that all members of the team were working in a symbiotic manner.



### Unified Software Process

In order to complete the steps of the lifecycle mentioned above, we used the Unified Software Process (USP), an extension of the Rational Unified Process (RUP) Model. This is an iterative and incremental framework that is focused on risk and is architecture-centric. The process contains 4 main phases: Inception, Elaboration, Construction, and Transition. Each of these phases contains either 1 or multiple of the phases of the software development lifecycle. By following the USP, we allowed ourselves to incrementally make changes to the design and implementation of the project without having to stop what we were doing. It also gave us a structure for how to model our application.

#### Inception

During this phase, we established the requirements for our project by collecting them from the stakeholders. We analyzed these requirements and came up with an initial Class diagram for the different components of our application. We analyzed the timetable of the project to understand our deadlines and the tasks to complete by each deadline.

- Main Lifecycle Phases: Requirements Gathering and Analysis, Design
- Entrance Criteria
  - The list of requirements to be analyzed
- Exit Criteria
  - An understanding of the application's requirements, discussed and clarified during initial team meetings
  - A class diagram detailing the different components of our application
  - A timetable for timely app deployment

#### Elaboration

During this phase, we elaborated on the requirements and starting describing our project architecture. We created designs to represent the components of our application and their interaction, use cases, database entities, and an initial list of test cases connected to the functionality denoted in our use cases. We built mockups of the UI to model the functionality of our application

- Main Lifecycle Phases: Requirements Gathering and Analysis, Design, Implementation

- Entrance Criteria
  - A clear understanding of the requirements, clarified and elaborated upon during meetings
- Exit Criteria
  - A project plan for meeting our deadlines and completing tasks
  - A class diagram which describes the classes representing the data for the application, along with class attributes and methods and relationships between classes.
  - A component diagram showing the relationships between the different components of the application, and the functions that are used to implement these relationships
  - A use case diagram represented the scenarios that functions of the application must complete
  - A list of elementary test cases

#### Construction

Once we had the design of our system architecture relatively complete, we went about implementing the system. We created classes connected to our components, a database connected to our entity-relationship diagram, and activities that carried out functionality defined in our use case documentation. We started implementing some of the test cases we outlined in the Elaboration phase.

- Main Lifecycle Phases: Design, Implementation, Testing

- Entrance Criteria
  - Thorough diagrams and documentation from the Design phase
- Exit Criteria
  - An initial version of the application that is more or less operational
  - Changes to the design if needed
  - An outline of test cases starting to be implemented

#### Transition

During this phase, we had an operational application. Testing is continued in order to catch any bugs or missing functionality that may need to be fixed or added. Final touches to the application are made, and a product is fine-tuned for release.

- Entrance Criteria
  - An initial version of the application with a well-defined test case outline
- Exit Criteria
  - More complete testing with test results and documentation
  - A completed version of the application ready for the stakeholders to analyze.

#### Summary

All of these steps, like the phases of the software development lifecycle, are iterative. They can be revisited and different aspects of each phase can be done in other phases. The process wasn't static. Rather, it was dynamic, ever-changing, and a great introduction to the world of software development and engineering.

## 3 Team

- Team Members
  - Shmuel Halbfinger
  - Jeffrey Tom
  - Luis Toro
  - Xin Huang Liu
  - Allan Gershon
  - Esteban Ansaldo
- Roles
  - Project Manager- Developer(s) in charge of managing the processes necessary for successful application design, development, and deployment. This member is in charge of making sure all documentation is complete and thorough, all designs represent accurate and concise implementation, and that the application runs smoothly and correctly. This member is also in charge of maintaining deadlines and calling meetings.
  - Backend Developer- Developers in charge of creating the functionality specified in the requirements. These developers create the components of the application and the activities that utilize these components. They also utilize database functionality in order to connect the components to their actual data. They are in charge of fixing bugs and making sure that use cases are implemented fully and correctly.
  - Front-end Developer- Developers in charge of building the User Interface. These developers were in charge of creating the graphical interface to actualize the data for the user to see and manipulate.
  - Database Developer- Developers in charge of constructing and maintaining the database. These developers were in charge of actualizing the components of the application in data and modeling the CRUD applications necessary to fulfill requirements.
  - QA Testing Developer- Developers in charge of coming up with test cases and implementing them. They are also in charge of making sure that test case results are documented and that these tests cover a necessary percentage of the functionality of the application. If there is a bug, these members are tasked with letting the developers know so that it can be fixed. Lastly, these members are responsible for final checks to the application before release.
- Table

| Role                 | Members                                                      | Backup Members (If Applicable) |
| -------------------- | ------------------------------------------------------------ | ------------------------------ |
| Project Manager      | Shmuel Halbfinger                                            | Jeffrey Tom                    |
| Backend Developers   | Esteban Ansaldo, Shmuel Halbfinger, Xin Huang Liu            |                                |
| Front-end Developer  | Jeffrey Tom, Luis Toro                                       |                                |
| Database Developer   | Shmuel Halbfinger, Allan Gershon, Esteban Ansaldo, Jeffrey Tom |                                |
| QA Testing Developer | Luis Toro, Xin Huang Liu, Esteban Ansaldo, Allan Gershon     |                                |

