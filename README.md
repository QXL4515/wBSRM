# Introduction
A monitor application based on environmental factor weight with TF-IDF algorithm and weighted naive Bayesian classifier.

(It's the 1.0.0 version of this application)

wBSRM Description

The executive capacity of service-oriented system relies on the third-party services. However, such reliance would result in many uncertainties, given the complex and changeable network environment. Hence, how to assure QoS of the third-party services is very important for service-oriented system. Effective monitoring technique towards QoS, which is an important measure of third-party service quality, is necessary to ensure quality control of Web service. Current monitoring approaches do not consider the influences of environment including the position of server, user usage, and the load at runtime. Ignoring these influences, which do exist among the runtime monitoring process, may cause monitoring approaches producing wrong results. To overcome this problem, this paper proposes a novel Web Service QoS monitoring approach sensitive to environmental factors called wBSRM (weighted Bayesian Runtime Monitor) based on weighted naive Bayesian classifiers and TF-IDF (Term Frequency-Inverse Document Frequency) algorithm. Experiments are conducted based on a public data set and simulated data set. The experimental results demonstrate that wBSRM is better than previous approaches by accurately calculating environmental factor weight with TF-IDF algorithm and weighted naive Bayesian classifier.

Implements of wBSRM：

This application has a total of 20 classes and interfaces, including five interfaces and 15 classes, a total of 1141 lines of codes. The application can be divided into the following modules:

*	Module calculating the impact factor weight table ll2WiC0 and ll2WiC1: interface TFIDFComputeWiis defined, and two class TFIDFComputeWiC0 and TFIDFComputeWiC1based on TF-IDF algorithm are defined to implement, respectively;
*	Module reading different types of impact factor data: interface TFIDFReadListDataFromTxtis defined, and there will be two concrete class to implement the input of impact factor, class TFIDFReadUserInformationDataFromTxtreads client data, and class TFIDFReadWebServiceInformationDataFromTxt reads server-side data, respectively;
*	Module reading QoS data (in this experiment only including response time and throughput): interface TFIDFReadDataFromTxt is defined, and there are two concrete classes to implement. class TFIDFReadRTDataFromTxt reads response time of the corresponding data, and class TFIDFReadTPDataFromTxtreads the corresponding data throughput;
*	Module calculating prior probability P(ci): interface TFIDFComputePlCXis defined and implemented by two class: TFIDFComputePlC0and TFIDFComputePlC1;
*	Module calculating priori probability i: interface TFIDFComputePreProCX is defined and implemented by two class TFIDFComputePreProC0 and TFIDFComputePreProC1, respectively;
*	Module calculating P(ci|X): With priori probabilityiand the posterior probability P(X|ci), P(ci|X) can be calculated: class TFIDFComputePCiX, with classes computeAftProC0or computeAftProC1together to achieve P(ci|X);
*	Module recording runtime information: Class TFIDFUserBean, class TFIDFWebServiceBean and class TFIDFComputeTimeare defined for runtime recording various parts of computation to achieve experimental analysis.
*	TFIDFMain is the main class, which is to achieve a flow sequence of the entire and UI design.

When you perform the program you can see the UI of this application :

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/GUI.jpg)

*	Button-“Open_UsrList” is used to open client side data and return the path for subsequent program；
*	Button-“Open_WSList” is used to open server side data of web service and return the path for subsequent program；
*	Button-“Open_TPInfo”is used to open QoS data of throughout and return the path for subsequent program；
*	Button-“Open_RTInfo”is used to open QoS data of response time and return the path for subsequent program；
*	Button-“Out_Path”is used to specify the output path of theexperimental statistical data;

And you will see follow labelled Textfield:

*	TextField-“To Input Predefine BETA:”is used to read pre-defined parameter β；
*	TextField-“To Input Standard QoS_VALUE:”is used to read pre-defined QoS properties.

#As An Example

First GUI：

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/GUI.jpg)

When you click the button “Open_UsrList”、“Open_WSList”、“Open_TPInfo”、“Open_RTInfo” to open your input data, you will see the following interface in turn：

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/Open%20userList.jpg)

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/WSList.jpg)

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/TPData.jpg)

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/RTData.jpg)

And then, you can click the button “Out_Path” to choose your output path like this:

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/Output_path.jpg)

Finally ,you can input your custom β value and QoS standard value，click the button "Start_Comput", you will see the statistical information in the path you ordered and the application interface will show like this：

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/Final_Output.jpg)

![alt text](https://raw.githubusercontent.com/QXL4515/wBSRM/master/Images/Output_Data.jpg)


#Quick Start
  This application is developed in the environment that JDK1.7.0_79 and eclipse4.4.2;
  
  You should make sure that your JDK version isn't lower than 1.7.0_79;
  
  To run it, you can ：
  
  1、Run it in the exlipse as a application;
  
  2、Export it into a runnable JAR file and then you can double click it to run OR you can run it in the command with the command "javaw -jar (Your custom jar name).jar".

#Further Reading
  Pengcheng Zhang, Yuan Zhuang, Hareton Leung, Wei Song, Yu Zhou:
  A Novel QoS Monitoring Approach Sensitive to Environmental Factors. 2015 IEEE International Conference on Web Services: 145-152


#Principal Contributors

Pengcheng Zhang, Researcher, pchzhang@hhu.edu.cn

Zhipeng He, Student, Zhipeng_He@outlook.com

