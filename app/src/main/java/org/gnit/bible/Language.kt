package org.gnit.bible

enum class Language {
    en, es, pt, de, fr, ru, nl, it, pl, uk, sv, zh, ko, ja;
}

enum class Translation(val language: Language, val year: Int) {
    // English World
    webus(Language.en, 2000), // webus 206 english, World English Bible
    kjv(Language.en, 1611), //King James Version

    // Latin America
    rvr09(Language.es, 1909), // rvr09,        Spanish,    Reina Valera
    tb(Language.pt, 1917),    // tb,           Portuguese, Tradução Brasileira

    // Europe
    delut(Language.de, 1912), // delut,        German,     Lutherbibel

    // UK already included in English above
    lsg(Language.fr, 1910),  // lsg,          French,     Louis Segond Bible
    sinod(Language.ru, 1876), // СИНОД(sinod), Russian,    Синодальный перевод
    svrj(Language.nl, 1888), // SV-RJ(svrj),  Dutch,      Statenvertaling Jongbloed-editie
    rdv24(Language.it, 1924), // rdv24,        Italian,    Revised Diodati Version

    // Spain  already included in Spanish above
    ubg(Language.pl, 2017), // ubg,          Polish,     Uwspółcześniona Biblia gdańska
    ubio(Language.uk, 1962), // ubio,         Ukrainian,  Біблія в пер. Івана Огієнка
    sven(Language.sv, 1917), // sven,         swedish,    1917 års kyrkobibel

    // North East Asia
    cunp(Language.zh, 1919),// cunp 48 chinese, Chinese Union Version with New Punctuation
    krv(Language.ko, 1961),// krv 88 korean, Korean Revised Version, 개역한글
    jc(Language.ja, 1955);

    fun isAssets() = (this == webus || this == jc)
}

enum class ReadingMode { SINGLE, BILINGUAL_SIDE, BILINGUAL_UNDER }

class TranslationPair(val mainTranslation: Translation, val subTranslation: Translation? = null, val readingMode: ReadingMode){

    init {
        if(readingMode == ReadingMode.SINGLE && subTranslation != null) throw IllegalArgumentException("when ReadingMode is SINGLE, subTranslation should be NULL but is trying to set $subTranslation")
        if((readingMode == ReadingMode.BILINGUAL_SIDE || readingMode == ReadingMode.BILINGUAL_UNDER) && subTranslation == null) throw IllegalArgumentException("when ReadingMode is BILINGUAL_*, subTranslation should be set, but is trying to set null")
    }

    companion object {
        const val PREF_KEY = "TRANSLATION_PAIR"
    }

    override fun toString() = "$mainTranslation:$subTranslation"
    fun of(encodedString: String): TranslationPair {
        val split = encodedString.split(":")
        return TranslationPair(Translation.valueOf(split[0]), Translation.valueOf(split[1]),ReadingMode.valueOf(split[2]))
    }
    fun isSingle() = (this.subTranslation == null)
    fun setSingle() = TranslationPair(this.mainTranslation, null, ReadingMode.SINGLE)
    fun setMain(mainTranslation: Translation) = TranslationPair(mainTranslation, this.subTranslation, this.readingMode)
    fun setSub(subTranslation: Translation) = TranslationPair(this.mainTranslation, subTranslation, this.readingMode)
    fun setReadingMode(readingMode: ReadingMode) = TranslationPair(this.mainTranslation, this.subTranslation, readingMode)
}

fun main(){
    Translation.entries.forEach { println(it.toString()) }
}