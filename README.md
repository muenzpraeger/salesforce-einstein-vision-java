# salesforce-einstein-vision-java

This repository showcases how to use the [Salesforce Einstein Vision API](https://metamind.readme.io/) using a Java based wrapper.

Please check the [product documentation](https://metamind.readme.io/) for general information about what the Salesforce Einstein Vision API is, how to use it and when it'll be available for you.


# Run-On-Your-Own

## Prerequisites

For running the app on your own you'll need to fulfill the following requirements:
* Access to a Salesforce org, i. e. a Developer Edition (You can [signup here for free](https://developer.salesforce.com/signup) if you don't have one).
* An API account for Salesforce MetaMind.

Please find the detailed instructions for how to setup access to the [MetaMind API here](https://metamind.readme.io/docs/what-you-need-to-call-api).

## Downloading the library

At the moment the library is not accessible via MavenCentral or JCenter.

### Gradle

The Java library is available in this custom repo.

```
repositories {
    maven {
        url  "http://dl.bintray.com/muenzpraeger/salesforce-maven" 
    }
}
```

Add this dependency to your build.gradle file.

```
compile 'com.winkelmeyer:salesforce-einstein-vision:1.1.0'
```

### Maven

Please make sure to reference in your pom.xml to the Bintray download page.

```
<profiles>
    <profile>
        <repositories>
            <repository>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
                <id>bintray-muenzpraeger-salesforce-maven</id>
                <name>bintray</name>
                <url>http://dl.bintray.com/muenzpraeger/salesforce-maven</url>
            </repository>
        </repositories>
        <pluginRepositories>
            <pluginRepository>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
                <id>bintray-muenzpraeger-salesforce-maven</id>
                <name>bintray-plugins</name>
                <url>http://dl.bintray.com/muenzpraeger/salesforce-maven</url>
            </pluginRepository>
        </pluginRepositories>
        <id>bintray</id>
    </profile>
</profiles>
<activeProfiles>
    <activeProfile>bintray</activeProfile>
</activeProfiles>
```

Then add this dependency to the pom.xml.

```
<dependency>
  <groupId>com.winkelmeyer</groupId>
  <artifactId>salesforce-einstein-vision</artifactId>
  <version>1.1.0</version>
  <type>pom</type>
</dependency>
```

## Getting started

As this library is basically a wrapper for using the [Salesforce Einstein Vision API](https://metamind.readme.io/) it is highly recommended to checkout the API documentation.

The workflow for using Einstein Vision can be described with the following scenario.

* First you identify image categories that need to be identified (i. e. Beaches, Mountains etc.).
* Then you create a dataset which will hold all those image categories.
* You add for each identified image category a so called "label" to the dataset.
* For every label you add a number of example images to the dataset.
* After you've finished adding examples you can train the dataset.
* After the training you can start to predict if a given image belongs to one of the defined labels.

You can find two examples [here in the GitHub repo](https://github.com/muenzpraeger/salesforce-einstein-vision-java/tree/master/src/test/java/com/winkelmeyer/salesforce/einsteinvision/tests) for the aforementioned workflow.

Please check the MetaMind documentation for recommendations regarding image quality, amount of examples and so forth.

### Creating a PredictionService

The foundation for everything is the `PredictionService`. As the communication with the API is based on a valid OAuth2 token (see MetaMind documentation) you can initiate a new PredictionService in two ways.

```
PredictionService predictionService = new PredictionService();
```
or
```
PredictionService predictionService = new PredictionService("AASDFNSADF23423424SADSFASF");
```

Based on your needs you can pass the OAuth2 token either as parameter (see 2nd example). Or you set it as an environment variable. In this case you don't have to pass the token.

### Creating a first Dataset

For creating a Dataset you call
```
Dataset ds = service.createDataset("theDatasetName", new String[]{"Beaches", "Mountains"});
```
If you decide later to add more labels (aka categories) to the Dataset you can do that via an available `createLabel` method.

### Adding Examples

For adding an Example to a Dataset you have to execute this call.#
```
Example example = service.createExample(theDatasetId, "customImageName", theLabelId, "localFilePath");
```
This will upload the file to the Dataset for the training.

### Train the Dataset

After you've added all Examples you can train the Dataset via
```
Model model = service.trainDataset(theDatasetId, "yourCustomTrainingName", 0, 0);
```
This will start the training process on the server.

### Predict an image

You can predict images either by sending Base64, uploading a local file or a remote (publicly available!) URL. See this example how to validate a remote URL.

```
PredictionResult result = service.predictUrl(modelId, beachUrl, "");
```

The returned result will give you an array of all Labels within the Dataset and their probability.

Checkout the aforementioned example code for a complete process.

## Development

If you want to develop with this code you can do it i. e. with Eclipse.

Running `gradle eclipse` in the project root folder (or `gradlew eclipse` if you don't have Gradle installed) will create the needed files, so that you can import the project as Eclipse project.

## Contribution

Feel free to contribute to this project via pull requests.

## License

For licensing see the included [license file](https://github.com/muenzpraeger/salesforce-einstein-vision-java/blob/master/LICENSE.md).
