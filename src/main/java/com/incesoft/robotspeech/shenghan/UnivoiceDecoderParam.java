package com.incesoft.robotspeech.shenghan;

public class UnivoiceDecoderParam {
	private float lmScale;
	private float amScale;
	private int maxTSN;
	private int minTSN;
	private float beamWidth;
	private float wordBeam;
	private float wordPenalty;
	private float loopPenalty;
	private float transPenalty;
	private int nBest;
	private int epsTransLimit;
	private int genPartialResult;
	private int genPartialFrame;
	private int debug;
	private float blankSkip;
	public UnivoiceDecoderParam(){
		
	}

	public UnivoiceDecoderParam(float lmScale, float amScale, int maxTSN,
			int minTSN, float beamWidth, float wordBeam, float wordPenalty,
			float loopPenalty, float transPenalty, int nBest,
			int epsTransLimit, int genPartialResult, int genPartialFrame,
			int debug, float blankSkip) {
		this.lmScale = lmScale;
		this.amScale = amScale;
		this.maxTSN = maxTSN;
		this.minTSN = minTSN;
		this.beamWidth = beamWidth;
		this.wordBeam = wordBeam;
		this.wordPenalty = wordPenalty;
		this.loopPenalty = loopPenalty;
		this.transPenalty = transPenalty;
		this.nBest = nBest;
		this.epsTransLimit = epsTransLimit;
		this.genPartialResult = genPartialResult;
		this.genPartialFrame = genPartialFrame;
		this.debug = debug;
		this.blankSkip = blankSkip;
	}

	public float getLmScale() {
		return lmScale;
	}

	public void setLmScale(float lmScale) {
		this.lmScale = lmScale;
	}

	public float getAmScale() {
		return amScale;
	}

	public void setAmScale(float amScale) {
		this.amScale = amScale;
	}

	public int getMaxTSN() {
		return maxTSN;
	}

	public void setMaxTSN(int maxTSN) {
		this.maxTSN = maxTSN;
	}

	public int getMinTSN() {
		return minTSN;
	}

	public void setMinTSN(int minTSN) {
		this.minTSN = minTSN;
	}

	public float getBeamWidth() {
		return beamWidth;
	}

	public void setBeamWidth(float beamWidth) {
		this.beamWidth = beamWidth;
	}

	public float getWordBeam() {
		return wordBeam;
	}

	public void setWordBeam(float wordBeam) {
		this.wordBeam = wordBeam;
	}

	public float getWordPenalty() {
		return wordPenalty;
	}

	public void setWordPenalty(float wordPenalty) {
		this.wordPenalty = wordPenalty;
	}

	public float getLoopPenalty() {
		return loopPenalty;
	}

	public void setLoopPenalty(float loopPenalty) {
		this.loopPenalty = loopPenalty;
	}

	public float getTransPenalty() {
		return transPenalty;
	}

	public void setTransPenalty(float transPenalty) {
		this.transPenalty = transPenalty;
	}

	public int getnBest() {
		return nBest;
	}

	public void setnBest(int nBest) {
		this.nBest = nBest;
	}

	public int getEpsTransLimit() {
		return epsTransLimit;
	}

	public void setEpsTransLimit(int epsTransLimit) {
		this.epsTransLimit = epsTransLimit;
	}

	public int getGenPartialResult() {
		return genPartialResult;
	}

	public void setGenPartialResult(int genPartialResult) {
		this.genPartialResult = genPartialResult;
	}

	public int getGenPartialFrame() {
		return genPartialFrame;
	}

	public void setGenPartialFrame(int genPartialFrame) {
		this.genPartialFrame = genPartialFrame;
	}

	public int getDebug() {
		return debug;
	}

	public void setDebug(int debug) {
		this.debug = debug;
	}

	public float getBlankSkip() {
		return blankSkip;
	}

	public void setBlankSkip(float blankSkip) {
		this.blankSkip = blankSkip;
	}

}
