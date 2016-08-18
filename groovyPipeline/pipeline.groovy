#!/usr/bin/env groovy
@Grab(group='de.tudarmstadt.ukp.dkpro.core', version='1.6.2',
      module='de.tudarmstadt.ukp.dkpro.core.opennlp-asl')
import de.tudarmstadt.ukp.dkpro.core.opennlp.*;
@Grab(group='de.tudarmstadt.ukp.dkpro.core', version='1.6.2',
      module='de.tudarmstadt.ukp.dkpro.core.languagetool-asl')
import de.tudarmstadt.ukp.dkpro.core.languagetool.*;
@Grab(group='de.tudarmstadt.ukp.dkpro.core', version='1.6.2',
      module='de.tudarmstadt.ukp.dkpro.core.maltparser-asl')
import de.tudarmstadt.ukp.dkpro.core.maltparser.*;
@Grab(group='de.tudarmstadt.ukp.dkpro.core', version='1.6.2',
      module='de.tudarmstadt.ukp.dkpro.core.io.text-asl')
import de.tudarmstadt.ukp.dkpro.core.io.text.*;
@Grab(group='de.tudarmstadt.ukp.dkpro.core', version='1.6.2',
      module='de.tudarmstadt.ukp.dkpro.core.io.conll-asl')
import de.tudarmstadt.ukp.dkpro.core.io.conll.*;

import static org.apache.uima.fit.pipeline.SimplePipeline.*;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.*;
import static org.apache.uima.fit.factory.CollectionReaderFactory.*;

runPipeline(
  createReaderDescription(TextReader,
    TextReader.PARAM_SOURCE_LOCATION, "document.txt",
    TextReader.PARAM_LANGUAGE, "en"),
  createEngineDescription(OpenNlpSegmenter),
  createEngineDescription(OpenNlpPosTagger),
  createEngineDescription(LanguageToolLemmatizer),
  createEngineDescription(MaltParser),
  createEngineDescription(Conll2006Writer,
    Conll2006Writer.PARAM_TARGET_LOCATION, "."));
