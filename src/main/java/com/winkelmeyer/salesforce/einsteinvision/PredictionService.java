package com.winkelmeyer.salesforce.einsteinvision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winkelmeyer.salesforce.einsteinvision.http.HttpClient;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartDataset;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartDatasetUrl;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartDatasetZipFile;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartExample;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartLabel;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartPrediction;
import com.winkelmeyer.salesforce.einsteinvision.http.parts.BodyPartTraining;
import com.winkelmeyer.salesforce.einsteinvision.model.Dataset;
import com.winkelmeyer.salesforce.einsteinvision.model.Example;
import com.winkelmeyer.salesforce.einsteinvision.model.Label;
import com.winkelmeyer.salesforce.einsteinvision.model.Model;
import com.winkelmeyer.salesforce.einsteinvision.model.ModelMetrics;
import com.winkelmeyer.salesforce.einsteinvision.model.PredictionResult;


public class PredictionService {

	// Base URL for the PredictionService
	private static String BASE_URL = "https://api.metamind.io/v1/vision";

	private String DATASETS = BASE_URL + "/datasets";
	private String LABELS = "/labels";
	private String EXAMPLES = "/examples";
	private String TRAIN = BASE_URL + "/train";
	private String MODELS = "/models";
	private String PREDICT = BASE_URL + "/predict";

	private ObjectMapper mapper = new ObjectMapper();
	private boolean isExecuting = false;
	private String bearerToken = System.getenv("EINSTEIN_VISION_BEARER");

	private static Logger logger = LoggerFactory.getLogger(PredictionService.class);

	/**
	 * <p>Create a new object of type PredictionService.</p>
	 * 
	 * <p>The PredictionService is the foundation for communicating with the Salesforce Einstein Vision Service.</p>
	 * 
	 * <p>As a pre-requisite for using the service a valid Bearer token must be stored in the environment variable EINSTEIN_VISION_BEARER.</p>
	 * 
	 * <p>Please use the constructor PreditionServer(String bearerToken) if you want to pass the token to the service instead of using the environment variable.
	 */
	public PredictionService() {
	}

	/**
	 * <p>Create a new object of type PredictionService.</p>
	 * 
	 * <p>The PredictionService is the foundation for communicating with the Salesforce Einstein Prediction Service.
	 * 
	 * @param bearerToken
	 * A valid Bearer token
	 */
	public PredictionService(String bearerToken) {
		this.bearerToken = bearerToken;
	}

	/**
	 * Creates a new Dataset. A Dataset is basically a group of different object types (named as "Label").
	 * @param name
	 * The name of the Dataset
	 * @param labels
	 * An array of labels that will be used in the Dataset, i. e. "Beaches", "Mountains" etc.
	 * @return
	 * @throws Exception
	 */
	public Dataset createDataset(String name, String[] labels) throws Exception {
		logger.info("Starting {} call with name {}", "createDataset", name);
		BodyPartDataset parts = new BodyPartDataset(name, labels);
		HttpClient client = new HttpClient(this, DATASETS, parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "createDataset");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset dataset = mapper.readValue(client.getData(), Dataset.class);
			logger.info("New Dataset with id {} has been created.", dataset.getId());
			return dataset;
		}
		return null;
	}
	
	/**
	 * Creates a new Dataset from a local zip file. A Dataset is basically a group of different object types (named as "Label").
	 * 
	 * - The maximum file size is 50 MB.
	 * - The Dataset will be named based on the zip file name.
	 * - Each object type has to be in a separate sub-folder which will be used as Label value.
	 * 
	 * @param filePath
	 * The local file path of the zip file.
	 * @return
	 * @throws Exception
	 */
	public Dataset createDatasetFromUrlAsynch(String url) throws Exception {
		logger.info("Starting {} call from remote zip file {}", "createDatasetFromUrlAsynch", url);
		BodyPartDatasetUrl parts = new BodyPartDatasetUrl(url);
		HttpClient client = new HttpClient(this, DATASETS + "/upload", parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "createDatasetFromUrlAsynch");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset dataset = mapper.readValue(client.getData(), Dataset.class);
			logger.info("New Dataset with id {} has been created.", dataset.getId());
			return dataset;
		}
		return null;
	}
	
	/**
	 * Creates a new Dataset from a remote zip file. A Dataset is basically a group of different object types (named as "Label").
	 * 
	 * - The maximum file size if 50 MB.
	 * - The Dataset will be named based on the zip file name.
	 * - Each object type has to be in a separate sub-folder which will be used as Label value.
	 * 
	 * @param filePath
	 * The local file path of the zip file.
	 * @return
	 * @throws Exception
	 */
	public Dataset createDatasetFromZipFileAsynch(String filePath) throws Exception {
		logger.info("Starting {} call from local zip file {}", "createDatasetFromZipFileAsynch", filePath);
		BodyPartDatasetZipFile parts = new BodyPartDatasetZipFile(filePath);
		HttpClient client = new HttpClient(this, DATASETS + "/upload", parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "createDatasetFromZipFileAsynch");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset dataset = mapper.readValue(client.getData(), Dataset.class);
			logger.info("New Dataset with id {} has been created.", dataset.getId());
			return dataset;
		}
		return null;
	}

	/**
	 * Gets an existing Dataset.
	 * @param id
	 * The id of the Dataset that needs to be fetched.
	 * @return
	 * @throws Exception
	 */
	public Dataset getDataset(int id) throws Exception {
		logger.info("Starting {} call with parameter {}", "getDataset", id);
		HttpClient client = new HttpClient(this, DATASETS + "/" + id);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getDataset");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset dataset = mapper.readValue(client.getData(), Dataset.class);
			logger.info("Dataset has been read.");
			return dataset;
		}
		return null;
	}

	/**
	 * Gets all Datasets of the authenticated user.
	 * @return
	 * An array of all Datasets
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	public Dataset[] getDatasets() throws Exception {
		logger.info("Starting {} call", "getDatasets");
		HttpClient client = new HttpClient(this, DATASETS);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getDatasets");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset[] datasets = mapper.readValue(client.getData(), Dataset[].class);
			logger.info("Datasets have been read.");
			return datasets;
		}
		return null;
	}

	/**
	 * Deletes an existing dataset.
	 * @param dataset
	 * The Dataset that should be deleted.
	 * @return
	 * True if the deletion was successful.
	 */
	public boolean deleteDataset(Dataset dataset) {
		return deleteDataset(dataset.getId());
	}

	/**
	 * Deletes an existing dataset.
	 * @param id
	 * The id of the Dataset that should be deleted.
	 * @return
	 * True if the deletion was successful.
	 */
	public boolean deleteDataset(int id) {
		logger.info("Starting {} call", "deleteDataset");
		HttpClient client = new HttpClient(this, DATASETS + "/" + id);
		client.isDelete(true);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "deleteDataset");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			logger.info("Dataset {} has been deleted.", id);
			return true;
		}
		return false;
	}

	/**
	 * Creates a new Label within an existing Dataset.
	 * @param datasetId
	 * The id of the Dataset.
	 * @param name
	 * The new Label name.
	 * @return
	 * @throws Exception
	 */
	public Label createLabel(int datasetId, String name) throws Exception {
		logger.info("Starting {} call with name '{}'", "createLabel", name);
		BodyPartLabel parts = new BodyPartLabel(name);
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + LABELS, parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "createLabel");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Label label = mapper.readValue(client.getData(), Label.class); 
			logger.info("Label '{}' has been created.", name);
			return label;
		}
		return null;
	}

	/**
	 * Gets an existing Label from a Dataset.
	 * @param datasetId
	 * The id of the associated Dataset.
	 * @param id
	 * The id of the Dataset that needs to be fetched.
	 * @return
	 * @throws Exception
	 */
	public Label getLabel(int datasetId, int id) throws Exception {
		logger.info("Starting {} call with id '{}'", "getLabel", id);
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + LABELS + "/" + id);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getLabel");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Label label = mapper.readValue(client.getData(), Label.class); 
			logger.info("Label '{}' has been read.", id);
			return label;
		}
		return null;
	}
	
	/**
	 * Creates new Examples from a remote zip file.
	 * 
	 * - The maximum file size is 50 MB.
	 * - The Dataset will be named based on the zip file name.
	 * - Each object type has to be in a separate sub-folder which will be used as Label value.
	 * 
	 * @param dataset
	 * The Dataset to which the Examples are associated.
	 * @param filePath
	 * The local file path of the zip file.
	 * @return
	 * @throws Exception
	 */
	public Dataset createExamplesFromUrl(Dataset dataset, String url) throws Exception {
		return createExamplesFromUrl(dataset.getId(), url);
	}
	
	/**
	 * Creates new Examples from a remote zip file.
	 * 
	 * - The maximum file size is 50 MB.
	 * - The Dataset will be named based on the zip file name.
	 * - Each object type has to be in a separate sub-folder which will be used as Label value.
	 * 
	 * @param datasetId
	 * The id of the Dataset to which the Examples are associated.
	 * @param filePath
	 * The local file path of the zip file.
	 * @return
	 * @throws Exception
	 */
	public Dataset createExamplesFromUrl(long datasetId, String url) throws Exception {
		logger.info("Starting {} call from remote zip file {}", "createExamplesFromUrlAsynch", url);
		BodyPartDatasetUrl parts = new BodyPartDatasetUrl(url);
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId, parts.build());
		client.setPut(true);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "createExamplesFromUrlAsynch");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset dataset = mapper.readValue(client.getData(), Dataset.class);
			logger.info("New examples for dataset with id {} have been uploaded.", dataset.getId());
			return dataset;
		}
		return null;
	}
	
	/**
	 * Creates new Examples from a local zip file.
	 * 
	 * - The maximum file size if 50 MB.
	 * - The Dataset will be named based on the zip file name.
	 * - Each object type has to be in a separate sub-folder which will be used as Label value.
	 * 
	 * @param dataset
	 * The Dataset to which the Examples are associated.
	 * @param filePath
	 * The local file path of the zip file.
	 * @return
	 * @throws Exception
	 */
	public Dataset createExamplesFromZipFile(Dataset dataset, String filePath) throws Exception {
		return createExamplesFromZipFile(dataset.getId(), filePath);
	}
	
	/**
	 * Creates new Examples from a local zip file.
	 * 
	 * - The maximum file size if 50 MB.
	 * - The Dataset will be named based on the zip file name.
	 * - Each object type has to be in a separate sub-folder which will be used as Label value.
	 * 
	 * @param datasetId
	 * The id of the Dataset to which the Examples are associated.
	 * @param filePath
	 * The local file path of the zip file.
	 * @return
	 * @throws Exception
	 */
	public Dataset createExamplesFromZipFile(long datasetId, String filePath) throws Exception {
		logger.info("Starting {} call from local zip file {}", "createExamplesFromZipFileAsynch", filePath);
		BodyPartDatasetZipFile parts = new BodyPartDatasetZipFile(filePath);
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId, parts.build());
		client.setPut(true);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "createExamplesFromZipFileAsynch");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Dataset dataset = mapper.readValue(client.getData(), Dataset.class);
			logger.info("New Dataset with id {} has been created.", dataset.getId());
			return dataset;
		}
		return null;
	}

	/**
	 * Adds a new image example for the vision training.
	 * @param datasetId
	 * The id of the Dataset to which the image should be added.
	 * @param name
	 * The name of the image.
	 * @param labelId
	 * The label to which the image should be associated.
	 * @param data
	 * The full file path of the image.
	 * @return
	 * @throws Exception
	 */
	public Example createExample(long datasetId, String name, long labelId, String data) throws Exception {
		logger.info("Starting {} call with name {}", "createExample", name);
		BodyPartExample parts = new BodyPartExample(name, labelId, data);
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + EXAMPLES, parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "createExample");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Example example = mapper.readValue(client.getData(), Example.class);
			logger.info("New Example with id {} has been created.", example.getId());
			return example;
		}
		return null;
	}

	/**
	 * Gets data about an existing example.
	 * @param datasetId
	 * The Dataset id to which the example belongs.
	 * @param id
	 * The id of the Example.
	 * @return
	 * @throws Exception
	 */
	public Example getExample(long datasetId, long id) throws Exception {
		logger.info("Starting {} call with id '{}'", "getExample", id);
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + EXAMPLES + "/" + id);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getExample");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Example example = mapper.readValue(client.getData(), Example.class); 
			logger.info("Example '{}' has been read.", id);
			return example;
		}
		return null;
	}

	/**
	 * Gets an array of all Examples that are associated with the given Dataset.
	 * @param dataset
	 * The Dataset to which the Examples are associated.
	 * @return
	 * @throws Exception
	 */
	public Example[] getExamples(Dataset dataset) throws Exception {
		return getExamples(dataset.getId());
	}

	/**
	 * Gets an array of all Examples that are associated with the given Dataset.
	 * @param datasetId
	 * The id of the Dataset to which the Examples are associated.
	 * @return
	 * @throws Exception
	 */
	public Example[] getExamples(long datasetId) throws Exception {
		logger.info("Starting {} call", "getExamples");
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + EXAMPLES);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getExamples");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Example[] examples = mapper.readValue(client.getData(), Example[].class);
			logger.info("Examples have been read.");
			return examples;
		}
		return null;
	}

	/**
	 * Deletes the given Example.
	 * @param example
	 * The Example that needs to be deleted.
	 * @return
	 * True if the deletion was successful.
	 */
	public boolean deleteExample(Example example) {
		return deleteExample(example.getLabel().getDatasetId(), example.getId());
	}

	/**
	 * Deletes the given Example.
	 * @param datasetId
	 * The Dataset id to which the Example belongs.
	 * @param id
	 * The id of the Example that needs to be deleted.
	 * @return
	 * True if the deletion was successful.
	 */
	public boolean deleteExample(long datasetId, int id) {
		logger.info("Starting {} call", "deleteExample");
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + EXAMPLES + "/" + id);
		client.isDelete(true);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "deleteExample");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			logger.info("Example {} has been deleted.", id);
			return true;
		}
		return false;
	}

	/**
	 * Starts the training of a Dataset.
	 * @param dataset
	 * The Dataset that should be trained.
	 * @param name
	 * The name of the Dataset.
	 * @param epochs
	 * Optional. The number of training iterations, valid values are between 1-100. Set to 0 if you want to use the default.
	 * @param learningRate
	 * Optional. The learning rate, valid values are betweed 0.0001 and 0.01. Set to 0 if you want to use the default.
	 * @return
	 * @throws Exception
	 */
	public Model trainDataset(Dataset dataset, String name, int epochs, double learningRate, String trainingParams) throws Exception {
		return trainDataset(dataset.getId(), name, epochs, learningRate, trainingParams);
	}

	/**
	 * Starts the training of a Dataset.
	 * @param datasetId
	 * The Dataset id that should be trained.
	 * @param name
	 * The name of the Dataset.
	 * @param epochs
	 * Optional. The number of training iterations, valid values are between 1-100. Set to 0 if you want to use the default.
	 * @param learningRate
	 * Optional. The learning rate, valid values are betweed 0.0001 and 0.01. Set to 0 if you want to use the default.
	 * @param trainParams
	 * Optional. Additional training parameters. Please see the documentation for them. Pass an empty string if you won't configure them.
	 */
	public Model trainDataset(int datasetId, String name, int epochs, double learningRate, String trainParams) throws Exception {
		Dataset dataset = getDataset(datasetId);
		if (dataset != null) {
			if (dataset.getTotalExamples()>0) {
				logger.info("Starting {} call with name {}", "trainDataset", name);
				BodyPartTraining parts = new BodyPartTraining(datasetId, name, epochs, learningRate);
				HttpClient client = new HttpClient(this, TRAIN, parts.build());
				logger.info("Target URL is {}", client.getUrl());
				client.execute();
				while(isExecuting()) {
					logger.info("Status is: {}", isExecuting());
				}
				logger.info("Call {} has been executed.", "trainDataset");
				if (client.isError()) {
					handleError(client.getResponseError());
				} else {
					Model model = mapper.readValue(client.getData(), Model.class);
					logger.info("New Model with id {} has been created.", model.getModelId());
					return model;
				}
			} else {
				logger.info("The dataset with id {} has no configured examples.", datasetId);
			}
		}
		return null;
	}

	/**
	 * Fetches the model for the given id.
	 * @param modelId
	 * The id of the model that needs to be fetched.
	 * @return
	 * @throws Exception
	 */
	public Model getModel(String modelId) throws Exception {
		logger.info("Starting {} call with parameter {}", "getModel", modelId);
		HttpClient client = new HttpClient(this, TRAIN + "/" + modelId);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getModel");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Model model = mapper.readValue(client.getData(), Model.class);
			logger.info("Model status has been read.");
			return model;
		}
		return null;
	}

	/**
	 * Gets the metrics of a model.
	 * @param modelMetricsId
	 * The model id for which the metrics should be fetched.
	 * @return
	 * @throws Exception
	 */
	public ModelMetrics getModelMetrics(String modelMetricsId) throws Exception {
		logger.info("Starting {} call with parameter {}", "getModelMetrics", modelMetricsId);
		HttpClient client = new HttpClient(this, MODELS + "/" + modelMetricsId);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getModelMetrics");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			ModelMetrics modelMetrics = mapper.readValue(client.getData(), ModelMetrics.class);
			logger.info("ModelMetrics have been read.");
			return modelMetrics;
		}
		return null;
	}

	/**
	 * Gets all trained Models that are trained for the given Dataset.
	 * @param dataset
	 * The Dataset to which the Models are assigned.
	 * @return
	 * @throws Exception
	 */
	public Model[] getModels(Dataset dataset) throws Exception {
		return getModels(dataset.getId());
	}

	/**
	 * Gets all trained Models that are trained for the given Dataset.
	 * @param datasetId
	 * The Dataset id to which the Models are assigned.
	 * @return
	 * @throws Exception
	 */
	public Model[] getModels(int datasetId) throws Exception {
		logger.info("Starting {} call", "getModels");
		HttpClient client = new HttpClient(this, DATASETS + "/" + datasetId + MODELS);
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while (isExecuting()) {
			logger.info("Status is: {}", isExecuting() );
		}
		logger.info("Call {} has been executed.", "getModels");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			Model[] models = mapper.readValue(client.getData(), Model[].class);
			logger.info("Models have been read.");
			return models;
		}
		return null;
	}

	/**
	 * Predicts the association of the given image in Base64 format to a trained model.
	 * @param model
	 * The Model that should be used for the prediction.
	 * @param base64
	 * The image that should be predicted.
	 * @param sampleId
	 * Optional. A string that gets returned as an association with the predicted image.
	 * @return
	 * @throws Exception
	 */
	public PredictionResult predictBase64(Model model, String base64, String sampleId) throws Exception {
		return predictBase64(model.getModelId(), base64, sampleId);
	}

	/**
	 * Predicts the association of the given image in Base64 format to a trained model.
	 * @param modelId
	 * The Model id that should be used for the prediction.
	 * @param base64
	 * The image that should be predicted.
	 * @param sampleId
	 * Optional. A string that gets returned as an association with the predicted image.
	 * @return
	 * @throws Exception
	 */
	public PredictionResult predictBase64(String modelId, String base64, String sampleId) throws Exception {
		logger.info("Starting {} call with model {} for base64", "predict", modelId);
		BodyPartPrediction parts = new BodyPartPrediction(modelId, base64, sampleId, BodyPartPrediction.Type.BASE64);
		HttpClient client = new HttpClient(this, PREDICT, parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "predict");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			PredictionResult predictions = mapper.readValue(client.getData(), PredictionResult.class);
			logger.info("Image has been predicted.");
			return predictions;
		}
		return null;
	}

	/**
	 * Predicts the association of the given image on the local file system to a trained model.
	 * @param model
	 * The Model that should be used for the prediction.
	 * @param filePath
	 * The absolute file path of the local image.
	 * @param sampleId
	 * Optional. A string that gets returned as an association with the predicted image.
	 * @return
	 * @throws Exception
	 */
	public PredictionResult predictFile(Model model, String filePath, String sampleId) throws Exception {
		return predictFile(model.getModelId(), filePath, sampleId);
	}

	/**
	 * Predicts the association of the given image on the local file system to a trained model.
	 * @param modelId
	 * The Model id that should be used for the prediction.
	 * @param filePath
	 * The absolute file path of the local image.
	 * @param sampleId
	 * Optional. A string that gets returned as an association with the predicted image.
	 * @return
	 * @throws Exception
	 */
	public PredictionResult predictFile(String modelId, String filePath, String sampleId) throws Exception {
		logger.info("Starting {} call with model {} for file", "predict", modelId);
		BodyPartPrediction parts = new BodyPartPrediction(modelId, filePath, sampleId, BodyPartPrediction.Type.FILE);
		HttpClient client = new HttpClient(this, PREDICT, parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "predict");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			PredictionResult predictions = mapper.readValue(client.getData(), PredictionResult.class);
			logger.info("Image has been predicted.");
			return predictions;
		}
		return null;
	}

	/**
	 * Predicts the association of the given image on a remote url m to a trained model.
	 * @param model
	 * The Model that should be used for the prediction.
	 * @param url
	 * The absolute url of the image.
	 * @param sampleId
	 * Optional. A string that gets returned as an association with the predicted image.
	 * @return
	 * @throws Exception
	 */
	public PredictionResult predictUrl(Model model, String url, String sampleId) throws Exception {
		return predictUrl(model.getModelId(), url, sampleId);
	}

	/**
	 * Predicts the association of the given image on a remote url m to a trained model.
	 * @param modelId
	 * The Model id that should be used for the prediction.
	 * @param url
	 * The absolute url of the image.
	 * @param sampleId
	 * Optional. A string that gets returned as an association with the predicted image.
	 * @return
	 * @throws Exception
	 */
	public PredictionResult predictUrl(String modelId, String url, String sampleId) throws Exception {
		logger.info("Starting {} call with model {} for url", "predict", modelId);
		BodyPartPrediction parts = new BodyPartPrediction(modelId, url, sampleId, BodyPartPrediction.Type.URL);
		HttpClient client = new HttpClient(this, PREDICT, parts.build());
		logger.info("Target URL is {}", client.getUrl());
		client.execute();
		while(isExecuting()) {
			logger.info("Status is: {}", isExecuting());
		}
		logger.info("Call {} has been executed.", "predict");
		if (client.isError()) {
			handleError(client.getResponseError());
		} else {
			PredictionResult predictions = mapper.readValue(client.getData(), PredictionResult.class);
			logger.info("Image has been predicted.");
			return predictions;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExecuting() {
		return isExecuting;
	}

	/**
	 * 
	 * @param isExecuting
	 */
	public void isExecuting(boolean isExecuting) {
		this.isExecuting = isExecuting;
	}

	/**
	 * 
	 * @param message
	 */
	private void handleError(String message) {
		logger.error("Response finished with Error: {}", message);
	}

	/**
	 * 
	 * @return
	 */
	public String getBearerToken() {
		return bearerToken;
	}

}
;