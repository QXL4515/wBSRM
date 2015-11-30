# wBSRM
A monitor application based on environmental factor weight with TF-IDF algorithm and weighted naive Bayesian classifier.

(It's a initial version of this application)

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

*	Button-“Open_UsrList” is used to open client side data and return the path for subsequent program；
*	Button-“Open_WSList” is used to open server side data of web service and return the path for subsequent program；
*	Button-“Open_TPInfo”is used to open QoS data of throughout and return the path for subsequent program；
*	Button-“Open_RTInfo”is used to open QoS data of response time and return the path for subsequent program；
*	Button-“Out_Path”is used to specify the output path of theexperimental statistical data;
*	TextField-“To Input Predefine BETA:”is used to read pre-defined parameter β；
*	TextField-“To Input Standard QoS_VALUE:”is used to read pre-defined QoS properties.

Principal Contributors

Pengcheng Zhang, Researcher, pchzhang@hhu.edu.cn

Zhipeng He, Student, Zhipeng_He@outlook.com

