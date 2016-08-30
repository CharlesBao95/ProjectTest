package FeatureGeneration.FeatureCluster;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;

public class ToVector {
	
	public static void main(String[] args){
		
		//Logger log = null
		//log.info("Load data....");
        SentenceIterator iter = new LineSentenceIterator(new File("File/corpus.txt"));
        
        int batchSize = 100;
        int iterations = 100;
        int layerSize = 10;
        
        //log.info("Build model....");
        Word2Vec vec = new Word2Vec.Builder()
                .batchSize(batchSize) //# words per minibatch.
                .minWordFrequency(5) // 
                //.useAdaGrad(false) //
                .layerSize(layerSize) // word feature vector size
                .iterations(iterations) // # iterations to train
                .learningRate(0.025) // 
                .minLearningRate(1e-3) // learning rate decays wrt # words. floor learning
                .negativeSample(10) // sample size 10 words
                .iterate(iter) //
                .negativeSample(1)
                .windowSize(5)
        
                //.tokenizerFactory(tokenizer)
                .build();
        vec.fit();
        
        try {
			WordVectorSerializer.writeWordVectors(vec, "words.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
	
}
