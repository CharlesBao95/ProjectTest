package FeatureGeneration.wordsimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ����һ����
 */
public class Word {
    private String word;
    private String type;

    /**
     * ��һ������ԭ��
     */
    private String firstPrimitive;

    /**
     * ����������ԭ��
     */
    private List<String> otherPrimitives = new ArrayList<String>();

    /**
     * �����list�ǿգ���ô���һ����ʡ� �б����ŵ��Ǹ���ʵ�һ����ԭ�����������������ʽ���
     */
    private List<String> structruralWords = new ArrayList<String>();

    /**
     * �ôʵĹ�ϵ��ԭ��key: ��ϵ��ԭ�� value�� ������ԭ|(�����)��һ���б�
     */
    private Map<String, List<String>> relationalPrimitives = new HashMap<String, List<String>>();

    /**
     * �ôʵĹ�ϵ������ԭ��Key: ��ϵ���š� value: ���ڸù�ϵ���ŵ�һ�������ԭ|(�����)
     */
    private Map<String, List<String>> relationSimbolPrimitives = new HashMap<String, List<String>>();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWord() {
        return word;
    }
    /**
     * �Ƿ�Ϊ���
     * @return
     */
    public boolean isStructruralWord(){
        return !structruralWords.isEmpty();
    }

    /**
     * DOCUMENT ME!
     *
     * @param word
     *            DOCUMENT ME!
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType() {
        return type;
    }

    /**
     * DOCUMENT ME!
     *
     * @param type
     *            DOCUMENT ME!
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFirstPrimitive() {
        return firstPrimitive;
    }

    /**
     * DOCUMENT ME!
     *
     * @param firstPrimitive
     *            DOCUMENT ME!
     */
    public void setFirstPrimitive(String firstPrimitive) {
        this.firstPrimitive = firstPrimitive;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List<String> getOtherPrimitives() {
        return otherPrimitives;
    }

    /**
     * DOCUMENT ME!
     *
     * @param otherPrimitives
     *            DOCUMENT ME!
     */
    public void setOtherPrimitives(List<String> otherPrimitives) {
        this.otherPrimitives = otherPrimitives;
    }

    /**
     * DOCUMENT ME!
     *
     * @param otherPrimitive
     *            DOCUMENT ME!
     */
    public void addOtherPrimitive(String otherPrimitive) {
        this.otherPrimitives.add(otherPrimitive);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List<String> getStructruralWords() {
        return structruralWords;
    }

    /**
     * DOCUMENT ME!
     *
     * @param structruralWords
     *            DOCUMENT ME!
     */
    public void setStructruralWords(List<String> structruralWords) {
        this.structruralWords = structruralWords;
    }

    /**
     * DOCUMENT ME!
     *
     * @param structruralWord
     *            DOCUMENT ME!
     */
    public void addStructruralWord(String structruralWord) {
        this.structruralWords.add(structruralWord);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param value DOCUMENT ME!
     */
    public void addRelationalPrimitive(String key, String value) {
        List<String> list = relationalPrimitives.get(key);

        if (list == null) {
            list = new ArrayList<String>();
            list.add(value);
            relationalPrimitives.put(key, list);
        } else {
            list.add(value);
        }
    }
    public void addRelationSimbolPrimitive(String key,String value){
        List<String> list = relationSimbolPrimitives.get(key);

        if (list == null) {
            list = new ArrayList<String>();
            list.add(value);
            relationSimbolPrimitives.put(key, list);
        } else {
            list.add(value);
        }
    }
    public Map<String, List<String>> getRelationalPrimitives() {
        return relationalPrimitives;
    }
    public Map<String, List<String>> getRelationSimbolPrimitives() {
        return relationSimbolPrimitives;
    }
}
