#!/usr/local/lib/jython jython
//Fix classpath scanning - otherise uimaFIT will not find the UIMA types
from java.lang import Thread
from org.python.core.imp import *
Thread.currentThread().contextClassLoader = getSyspathJavaLoader()

//Dependencies and imports for DKPro modules
from jip.embed import require
require('de.tudarmstadt.ukp.dkpro.core:de.tudarmstadt.ukp.dkpro.core.opennlp-asl:1.6.2')
from de.tudarmstadt.ukp.dkpro.core.opennlp import *
require('de.tudarmstadt.ukp.dkpro.core:de.tudarmstadt.ukp.dkpro.core.languagetool-asl:1.6.2')
from de.tudarmstadt.ukp.dkpro.core.languagetool import *
require('de.tudarmstadt.ukp.dkpro.core:de.tudarmstadt.ukp.dkpro.core.maltparser-asl:1.6.2')
from de.tudarmstadt.ukp.dkpro.core.maltparser import *
require('de.tudarmstadt.ukp.dkpro.core:de.tudarmstadt.ukp.dkpro.core.io.text-asl:1.6.2')
from de.tudarmstadt.ukp.dkpro.core.io.text import *
require('de.tudarmstadt.ukp.dkpro.core:de.tudarmstadt.ukp.dkpro.core.io.conll-asl:1.6.2')
from de.tudarmstadt.ukp.dkpro.core.io.conll import *

//uimaFIT imports
from org.apache.uima.fit.pipeline.SimplePipeline import *
from org.apache.uima.fit.factory.AnalysisEngineFactory import *
from org.apache.uima.fit.factory.CollectionReaderFactory import *

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
