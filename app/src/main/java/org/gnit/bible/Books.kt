package org.gnit.bible

import android.util.SparseArray

/**
 * Created by Joel on 10/22/2014.
 */
object Books {
    fun maxChapter(book: Int): Int = when (book) {
        19 -> 150
        23 -> 66
        24 -> 52
        1 -> 50
        26 -> 48
        18 -> 42
        2 -> 40
        4, 14 -> 36
        5 -> 34
        9, 20 -> 31
        13 -> 29
        40, 44 -> 28
        3 -> 27
        12 -> 25
        6, 10, 42 -> 24
        11, 66 -> 22
        7, 43 -> 21
        41, 45, 46 -> 16
        28, 38 -> 14
        16, 47, 58 -> 13
        21, 27 -> 12
        15, 17 -> 10
        30 -> 9
        22 -> 8
        33 -> 7
        48, 49, 54 -> 6
        25, 52, 59, 60, 62 -> 5
        8, 32, 39, 50, 51, 55 -> 4
        29, 34, 35, 36, 53, 56, 61 -> 3
        37 -> 2
        31, 57, 63, 64, 65 -> 1
        else -> 50
    }

    val ENGLISH_NUMBER_NAME_MAP = englishBooks
    private val englishBooks: SparseArray<String>
        get() {

            val map = SparseArray<String>(67)

            map.put(1, "Genesis")
            map.put(2, "Exodus")
            map.put(3, "Leviticus")
            map.put(4, "Numbers")
            map.put(5, "Deuteronomy")
            map.put(6, "Joshua")
            map.put(7, "Judges")
            map.put(8, "Ruth")
            map.put(9, "1 Samuel")
            map.put(10, "2 Samuel")
            map.put(11, "1 Kings")
            map.put(12, "2 Kings")
            map.put(13, "1 Chronicles")
            map.put(14, "2 Chronicles")
            map.put(15, "Ezra")
            map.put(16, "Nehemiah")
            map.put(17, "Esther")
            map.put(18, "Job")
            map.put(19, "Psalms")
            map.put(20, "Proverbs")
            map.put(21, "Ecclesiastes")
            map.put(22, "Song of Solomon")
            map.put(23, "Isaiah")
            map.put(24, "Jeremiah")
            map.put(25, "Lamentations")
            map.put(26, "Ezekiel")
            map.put(27, "Daniel")
            map.put(28, "Hosea")
            map.put(29, "Joel")
            map.put(30, "Amos")
            map.put(31, "Obadiah")
            map.put(32, "Jonah")
            map.put(33, "Micah")
            map.put(34, "Nahum")
            map.put(35, "Habakkuk")
            map.put(36, "Zephaniah")
            map.put(37, "Haggai")
            map.put(38, "Zechariah")
            map.put(39, "Malachi")
            map.put(40, "Matthew")
            map.put(41, "Mark")
            map.put(42, "Luke")
            map.put(43, "John")
            map.put(44, "Acts")
            map.put(45, "Romans")
            map.put(46, "1 Corinthians")
            map.put(47, "2 Corinthians")
            map.put(48, "Galatians")
            map.put(49, "Ephesians")
            map.put(50, "Philippians")
            map.put(51, "Colossians")
            map.put(52, "1 Thessalonians")
            map.put(53, "2 Thessalonians")
            map.put(54, "1 Timothy")
            map.put(55, "2 Timothy")
            map.put(56, "Titus")
            map.put(57, "Philemon")
            map.put(58, "Hebrews")
            map.put(59, "James")
            map.put(60, "1 Peter")
            map.put(61, "2 Peter")
            map.put(62, "1 John")
            map.put(63, "2 John")
            map.put(64, "3 John")
            map.put(65, "Jude")
            map.put(66, "Revelation")
            return map
        }

    val SPANISH_NUMBER_NAME_MAP = spanishBooks
    private val spanishBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "Génesis")
            map.put(2, "Éxodo")
            map.put(3, "Levítico")
            map.put(4, "Números")
            map.put(5, "Deuteronomio")
            map.put(6, "Josué")
            map.put(7, "Jueces")
            map.put(8, "Rut")
            map.put(9, "1 Samuel")
            map.put(10, "2 Samuel")
            map.put(11, "1 Reyes")
            map.put(12, "2 Reyes")
            map.put(13, "1 Crónicas")
            map.put(14, "2 Crónicas")
            map.put(15, "Esdras")
            map.put(16, "Nehemías")
            map.put(17, "Ester")
            map.put(18, "Job")
            map.put(19, "Salmos")
            map.put(20, "Proverbios")
            map.put(21, "Eclesiastés")
            map.put(22, "Cantares")
            map.put(23, "Isaías")
            map.put(24, "Jeremías")
            map.put(25, "Lamentaciones")
            map.put(26, "Ezequiel")
            map.put(27, "Daniel")
            map.put(28, "Oseas")
            map.put(29, "Joel")
            map.put(30, "Amós")
            map.put(31, "Abdías")
            map.put(32, "Jonás")
            map.put(33, "Miqueas")
            map.put(34, "Nahúm")
            map.put(35, "Habacuc")
            map.put(36, "Sofonías")
            map.put(37, "Hageo")
            map.put(38, "Zacarías")
            map.put(39, "Malaquías")
            map.put(40, "Mateo")
            map.put(41, "Marcos")
            map.put(42, "Lucas")
            map.put(43, "Juan")
            map.put(44, "Hechos")
            map.put(45, "Romanos")
            map.put(46, "1 Corintios")
            map.put(47, "2 Corintios")
            map.put(48, "Gálatas")
            map.put(49, "Efesios")
            map.put(50, "Filipenses")
            map.put(51, "Colosenses")
            map.put(52, "1 Tesalonicenses")
            map.put(53, "2 Tesalonicenses")
            map.put(54, "1 Timoteo")
            map.put(55, "2 Timoteo")
            map.put(56, "Tito")
            map.put(57, "Filemón")
            map.put(58, "Hebreos")
            map.put(59, "Santiago")
            map.put(60, "1 Pedro")
            map.put(61, "2 Pedro")
            map.put(62, "1 Juan")
            map.put(63, "2 Juan")
            map.put(64, "3 Juan")
            map.put(65, "Judas")
            map.put(66, "Apocalipsis")

            return map
        }

    val PORTUGUESE_NUMBER_NAME_MAP = portugueseBooks
    private val portugueseBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "Gênesis")
            map.put(2, "Êxodo")
            map.put(3, "Levítico")
            map.put(4, "Números")
            map.put(5, "Deuteronômio")
            map.put(6, "Josué")
            map.put(7, "Juízes")
            map.put(8, "Rute")
            map.put(9, "1Samuel")
            map.put(10, "2Samuel")
            map.put(11, "1Reis")
            map.put(12, "2Reis")
            map.put(13, "1Crônicas")
            map.put(14, "2Crônicas")
            map.put(15, "Esdras")
            map.put(16, "Neemias")
            map.put(17, "Ester")
            map.put(18, "Jó")
            map.put(19, "Salmos")
            map.put(20, "Provérbios")
            map.put(21, "Eclesiastes")
            map.put(22, "Cântico")
            map.put(23, "Isaías")
            map.put(24, "Jeremias")
            map.put(25, "Lamentações")
            map.put(26, "Ezequiel")
            map.put(27, "Daniel")
            map.put(28, "Oseias")
            map.put(29, "Joel")
            map.put(30, "Amós")
            map.put(31, "Obadias")
            map.put(32, "Jonas")
            map.put(33, "Miqueias")
            map.put(34, "Naum")
            map.put(35, "Habacuque")
            map.put(36, "Sofonias")
            map.put(37, "Ageu")
            map.put(38, "Zacarias")
            map.put(39, "Malaquias")
            map.put(40, "Mateus")
            map.put(41, "Marcos")
            map.put(42, "Lucas")
            map.put(43, "João")
            map.put(44, "Atos")
            map.put(45, "Romanos")
            map.put(46, "1Coríntios")
            map.put(47, "2Coríntios")
            map.put(48, "Gálatas")
            map.put(49, "Efésios")
            map.put(50, "Filipenses")
            map.put(51, "Colossenses")
            map.put(52, "1Tessalonicenses")
            map.put(53, "2Tessalonicenses")
            map.put(54, "1Timóteo")
            map.put(55, "2Timóteo")
            map.put(56, "Tito")
            map.put(57, "Filemom")
            map.put(58, "Hebreus")
            map.put(59, "Tiago")
            map.put(60, "1Pedro")
            map.put(61, "2Pedro")
            map.put(62, "1João")
            map.put(63, "2João")
            map.put(64, "3João")
            map.put(65, "Judas")
            map.put(66, "Apocalipse")

            return map
        }

    val GERMAN_NUMBER_NAME_MAP = germanBooks
    private val germanBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "1. Mose")
            map.put(2, "2. Mose")
            map.put(3, "3. Mose")
            map.put(4, "4. Mose")
            map.put(5, "5. Mose")
            map.put(6, "Josua")
            map.put(7, "Richter")
            map.put(8, "Rut")
            map.put(9, "1. Samuel")
            map.put(10, "2. Samuel")
            map.put(11, "1. Könige")
            map.put(12, "2. Könige")
            map.put(13, "1. Chronik")
            map.put(14, "2. Chronik")
            map.put(15, "Esra")
            map.put(16, "Nehemia")
            map.put(17, "Ester")
            map.put(18, "Hiob")
            map.put(19, "Psalm")
            map.put(20, "Sprüche")
            map.put(21, "Prediger")
            map.put(22, "Hohelied")
            map.put(23, "Jesaja")
            map.put(24, "Jeremia")
            map.put(25, "Klagelieder")
            map.put(26, "Hesekiel")
            map.put(27, "Daniel")
            map.put(28, "Hosea")
            map.put(29, "Joel")
            map.put(30, "Amos")
            map.put(31, "Obadja")
            map.put(32, "Jona")
            map.put(33, "Micha")
            map.put(34, "Nahum")
            map.put(35, "Habakuk")
            map.put(36, "Zephanja")
            map.put(37, "Haggai")
            map.put(38, "Sacharja")
            map.put(39, "Maleachi")
            map.put(40, "Matthäus")
            map.put(41, "Markus")
            map.put(42, "Lukas")
            map.put(43, "Johannes")
            map.put(44, "Apostelgeschichte")
            map.put(45, "Römer")
            map.put(46, "1. Korinther")
            map.put(47, "2. Korinther")
            map.put(48, "Galater")
            map.put(49, "Epheser")
            map.put(50, "Philipper")
            map.put(51, "Kolosser")
            map.put(52, "1. Thessalonicher")
            map.put(53, "2. Thessalonicher")
            map.put(54, "1. Timotheus")
            map.put(55, "2. Timotheus")
            map.put(56, "Titus")
            map.put(57, "Philemon")
            map.put(58, "Hebräer")
            map.put(59, "Jakobus")
            map.put(60, "1. Petrus")
            map.put(61, "2. Petrus")
            map.put(62, "1. Johannes")
            map.put(63, "2. Johannes")
            map.put(64, "3. Johannes")
            map.put(65, "Judas")
            map.put(66, "Offenbarung")

            return map
        }

    val FRENCH_NUMBER_NAME_MAP = frenchBooks
    private val frenchBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "Genèse")
            map.put(2, "Exode")
            map.put(3, "Lévitique")
            map.put(4, "Nombres")
            map.put(5, "Deutéronome")
            map.put(6, "Josué")
            map.put(7, "Juges")
            map.put(8, "Ruth")
            map.put(9, "1 Samuel")
            map.put(10, "2 Samuel")
            map.put(11, "1 Rois")
            map.put(12, "2 Rois")
            map.put(13, "1 Chroniques")
            map.put(14, "2 Chroniques")
            map.put(15, "Esdras")
            map.put(16, "Néhémie")
            map.put(17, "Esther")
            map.put(18, "Job")
            map.put(19, "Psaumes")
            map.put(20, "Proverbes")
            map.put(21, "Ecclésiaste")
            map.put(22, "Cantique")
            map.put(23, "Ésaïe")
            map.put(24, "Jérémie")
            map.put(25, "Lamentations")
            map.put(26, "Ézékiel")
            map.put(27, "Daniel")
            map.put(28, "Osée")
            map.put(29, "Joël")
            map.put(30, "Amos")
            map.put(31, "Abdias")
            map.put(32, "Jonas")
            map.put(33, "Michée")
            map.put(34, "Nahum")
            map.put(35, "Habacuc")
            map.put(36, "Sophonie")
            map.put(37, "Aggée")
            map.put(38, "Zacharie")
            map.put(39, "Malachie")
            map.put(40, "Matthieu")
            map.put(41, "Marc")
            map.put(42, "Luc")
            map.put(43, "Jean")
            map.put(44, "Actes")
            map.put(45, "Romains")
            map.put(46, "1 Corinthiens")
            map.put(47, "2 Corinthiens")
            map.put(48, "Galates")
            map.put(49, "Éphésiens")
            map.put(50, "Philippiens")
            map.put(51, "Colossiens")
            map.put(52, "1 Thessaloniciens")
            map.put(53, "2 Thessaloniciens")
            map.put(54, "1 Timothée")
            map.put(55, "2 Timothée")
            map.put(56, "Tite")
            map.put(57, "Philémon")
            map.put(58, "Hébreux")
            map.put(59, "Jacques")
            map.put(60, "1 Pierre")
            map.put(61, "2 Pierre")
            map.put(62, "1 Jean")
            map.put(63, "2 Jean")
            map.put(64, "3 Jean")
            map.put(65, "Jude")
            map.put(66, "Apocalypse")

            return map
        }

    val RUSSIAN_NUMBER_NAME_MAP = russianBooks
    private val russianBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "Бытие")
            map.put(2, "Исход")
            map.put(3, "Левит")
            map.put(4, "Числа")
            map.put(5, "Второзаконие")
            map.put(6, "Книга Иисуса Навина")
            map.put(7, "Книга Судей израилевых")
            map.put(8, "Книга Руфи")
            map.put(9, "Первая книга Царств")
            map.put(10, "Вторая книга Царств")
            map.put(11, "Третья книга Царств")
            map.put(12, "Четвертая книга Царств")
            map.put(13, "Первая книга Паралипоменон")
            map.put(14, "Вторая книга Паралипоменон")
            map.put(15, "Первая книга Ездры")
            map.put(16, "Книга Неемии")
            map.put(17, "Есфирь")
            map.put(18, "Книга Иова")
            map.put(19, "Псалтирь")
            map.put(20, "Притчи Соломона")
            map.put(21, "Книга Екклезиаста, или Проповедника")
            map.put(22, "Песнь песней Соломона")
            map.put(23, "Книга пророка Исаии")
            map.put(24, "Книга пророка Иеремии")
            map.put(25, "Плач Иеремии")
            map.put(26, "Книга пророка Иезекииля")
            map.put(27, "Книга пророка Даниила")
            map.put(28, "Книга пророка Осии")
            map.put(29, "Книга пророка Иоиля")
            map.put(30, "Книга пророка Амоса")
            map.put(31, "Книга пророка Авдия")
            map.put(32, "Книга пророка Ионы")
            map.put(33, "Книга пророка Михея")
            map.put(34, "Книга пророка Наума")
            map.put(35, "Книга пророка Аввакума")
            map.put(36, "Книга пророка Софонии")
            map.put(37, "Книга пророка Аггея")
            map.put(38, "Книга пророка Захарии")
            map.put(39, "Книга пророка Малахии")
            map.put(40, "От Матфея святое благовествование")
            map.put(41, "От Марка святое благовествование")
            map.put(42, "От Луки святое благовествование")
            map.put(43, "От Иоанна святое благовествование")
            map.put(44, "Деяния святых апостолов")
            map.put(45, "Послание к Римлянам")
            map.put(46, "Первое послание к Коринфянам")
            map.put(47, "Второе послание к Коринфянам")
            map.put(48, "Послание к Галатам")
            map.put(49, "Послание к Ефесянам")
            map.put(50, "Послание к Филиппийцам")
            map.put(51, "Послание к Колоссянам")
            map.put(52, "Первое послание к Фессалоникийцам")
            map.put(53, "Второе послание к Фессалоникийцам")
            map.put(54, "Первое послание к Тимофею")
            map.put(55, "Второе послание к Тимофею")
            map.put(56, "Послание к Титу")
            map.put(57, "Послание к Филимону")
            map.put(58, "Послание к Евреям")
            map.put(59, "Послание Иакова")
            map.put(60, "Первое послание Петра")
            map.put(61, "Второе послание Петра")
            map.put(62, "Первое послание Иоанна")
            map.put(63, "Второе послание Иоанна")
            map.put(64, "Третье послание Иоанна")
            map.put(65, "Послание Иуды")
            map.put(66, "Откровение ап. Иоанна Богослова")

            return map
        }

    val DUTCH_NUMBER_NAME_MAP = dutchBooks
    private val dutchBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "GENESIS")
            map.put(2, "EXODUS")
            map.put(3, "LEVITICUS")
            map.put(4, "NUMERI")
            map.put(5, "DEUTERONOMIUM")
            map.put(6, "JOZUA")
            map.put(7, "RICHTEREN")
            map.put(8, "RUTH")
            map.put(9, "1 SAMUËL")
            map.put(10, "2 SAMUËL")
            map.put(11, "1 KONINGEN")
            map.put(12, "2 KONINGEN")
            map.put(13, "1 KRONIEKEN")
            map.put(14, "2 KRONIEKEN")
            map.put(15, "EZRA")
            map.put(16, "NEHEMIA")
            map.put(17, "ESTHER")
            map.put(18, "JOB")
            map.put(19, "PSALMEN")
            map.put(20, "SPREUKEN")
            map.put(21, "PREDIKER")
            map.put(22, "HOOGLIED")
            map.put(23, "JESAJA")
            map.put(24, "JEREMIA")
            map.put(25, "KLAAGLIEDEREN VAN JEREMIA")
            map.put(26, "EZECHIËL")
            map.put(27, "DANIËL")
            map.put(28, "HOSÉA")
            map.put(29, "JOËL")
            map.put(30, "AMOS")
            map.put(31, "OBADJA")
            map.put(32, "JONA")
            map.put(33, "MICHA")
            map.put(34, "NAHUM")
            map.put(35, "HABAKUK")
            map.put(36, "ZEFANJA")
            map.put(37, "HAGGAÏ")
            map.put(38, "ZACHARIA")
            map.put(39, "MALEACHI")
            map.put(40, "MATTHEÜS")
            map.put(41, "MARKUS")
            map.put(42, "LUKAS")
            map.put(43, "JOHANNES")
            map.put(44, "HANDELINGEN")
            map.put(45, "ROMEINEN")
            map.put(46, "1 KORINTHE")
            map.put(47, "2 KORINTHE")
            map.put(48, "GALATEN")
            map.put(49, "EFEZE")
            map.put(50, "FILIPPENZEN")
            map.put(51, "KOLOSSENZEN")
            map.put(52, "1 THESSALONICENZEN")
            map.put(53, "2 THESSALONICENZEN")
            map.put(54, "1 TIMÓTHEÜS")
            map.put(55, "2 TIMÓTHEÜS")
            map.put(56, "TITUS")
            map.put(57, "FILÉMON")
            map.put(58, "HEBREEËN")
            map.put(59, "JAKOBUS")
            map.put(60, "1 PETRUS")
            map.put(61, "2 PETRUS")
            map.put(62, "1 JOHANNES")
            map.put(63, "2 JOHANNES")
            map.put(64, "3 JOHANNES")
            map.put(65, "JUDAS")
            map.put(66, "OPENBARING")

            return map
        }

    val ITALIAN_NUMBER_NAME_MAP = italianBooks
    private val italianBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "GENESI")
            map.put(2, "ESODO")
            map.put(3, "LEVITICO")
            map.put(4, "NUMERI")
            map.put(5, "DEUTERONOMIO")
            map.put(6, "GIOSUÈ")
            map.put(7, "GIUDICI")
            map.put(8, "RUTH")
            map.put(9, "I SAMUELE")
            map.put(10, "II SAMUELE")
            map.put(11, "I RE")
            map.put(12, "II RE")
            map.put(13, "I CRONACHE")
            map.put(14, "II CRONACHE")
            map.put(15, "ESDRA")
            map.put(16, "NEHEMIA")
            map.put(17, "ESTER")
            map.put(18, "GIOBBE")
            map.put(19, "SALMI")
            map.put(20, "PROVERBI")
            map.put(21, "ECCLESIASTE")
            map.put(22, "CANTICO DE'~CANTICI")
            map.put(23, "ISAIA")
            map.put(24, "GEREMIA")
            map.put(25, "LAMENTAZIONI")
            map.put(26, "EZECHIELE")
            map.put(27, "DANIELE")
            map.put(28, "OSEA")
            map.put(29, "GIOELE")
            map.put(30, "AMOS")
            map.put(31, "ABDIA")
            map.put(32, "GIONA")
            map.put(33, "MICHEA")
            map.put(34, "NAHUM")
            map.put(35, "HABACUC")
            map.put(36, "SOFONIA")
            map.put(37, "AGGEO")
            map.put(38, "ZACCARIA")
            map.put(39, "MALACHIA")
            map.put(40, "Matteo")
            map.put(41, "Marco")
            map.put(42, "Luca")
            map.put(43, "Giovanni")
            map.put(44, "ATTI DEGLI APOSTOLI")
            map.put(45, "EPISTOLE DI S. PAOLO AI~ROMANI")
            map.put(46, "I Corinzi")
            map.put(47, "II Corinzi")
            map.put(48, "EPISTOLE DI S. PAOLO AI GALATI")
            map.put(49, "EPISTOLE DI S. PAOLO AGLI EFESINI")
            map.put(50, "EPISTOLE DI S. PAOLO AI FILIPPESI")
            map.put(51, "EPISTOLE DI S. PAOLO AI COLOSSESI")
            map.put(52, "EPISTOLE DI S. PAOLO I AI TESSALONICESI")
            map.put(53, "EPISTOLE DI S. PAOLO II AI TESSALONICESI")
            map.put(54, "EPISTOLE DI S. PAOLO I A TIMOTEO")
            map.put(55, "EPISTOLE DI S. PAOLO II A TIMOTEO")
            map.put(56, "EPISTOLE DI S. PAOLO A TITO")
            map.put(57, "EPISTOLE DI S. PAOLO A FILEMONE")
            map.put(58, "EPISTOLA AGLI EBREI")
            map.put(59, "EPISTOLA DI S. GIACOMO")
            map.put(60, "EPISTOLA I DI S. PIETRO")
            map.put(61, "EPISTOLA II DI S. PIETRO")
            map.put(62, "EPISTOLA I DI S. GIOVANNI")
            map.put(63, "EPISTOLA II DI S. GIOVANNI")
            map.put(64, "EPISTOLA III DI S. GIOVANNI")
            map.put(65, "EPISTOLA DI S. GIUDA")
            map.put(66, "APOCALISSE")

            return map
        }

    val POLISH_NUMBER_NAME_MAP = polishBooks
    private val polishBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "Rodzaju")
            map.put(2, "Wyjścia")
            map.put(3, "Kapłańska")
            map.put(4, "Liczb")
            map.put(5, "Powtórzonego")
            map.put(6, "Jozuego")
            map.put(7, "Księga Sędziów")
            map.put(8, "Rut")
            map.put(9, "I Samuela")
            map.put(10, "II Samuela")
            map.put(11, "I Królewska")
            map.put(12, "II Królewska")
            map.put(13, "I Kronik")
            map.put(14, "II Kronik")
            map.put(15, "Ezdrasza")
            map.put(16, "Nehemiasza")
            map.put(17, "Estery")
            map.put(18, "Hioba")
            map.put(19, "Psalmów")
            map.put(20, "Przysłów")
            map.put(21, "Kaznodziei")
            map.put(22, "Pieśń nad Pieśniami")
            map.put(23, "Izajasza")
            map.put(24, "Jeremiasza")
            map.put(25, "Lamentacje")
            map.put(26, "Ezechiela")
            map.put(27, "Daniela")
            map.put(28, "Ozeasza")
            map.put(29, "Joela")
            map.put(30, "Amosa")
            map.put(31, "Abdiasza")
            map.put(32, "Jonasza")
            map.put(33, "Micheasza")
            map.put(34, "Nahuma")
            map.put(35, "Habakuka")
            map.put(36, "Sofoniasza")
            map.put(37, "Aggeusza")
            map.put(38, "Zachariasza")
            map.put(39, "Malachiasza")
            map.put(40, "Mateusza")
            map.put(41, "Marka")
            map.put(42, "Łukasza")
            map.put(43, "Jana")
            map.put(44, "Dzieje")
            map.put(45, "Rzymian")
            map.put(46, "I Koryntian")
            map.put(47, "II Koryntian")
            map.put(48, "Galacjan")
            map.put(49, "Efezjan")
            map.put(50, "Filipian")
            map.put(51, "Kolosan")
            map.put(52, "I Tesaloniczan")
            map.put(53, "II Tesaloniczan")
            map.put(54, "I Tymoteusza")
            map.put(55, "II Tymoteusza")
            map.put(56, "Tytusa")
            map.put(57, "Filemona")
            map.put(58, "Hebrajczyków")
            map.put(59, "Jakuba")
            map.put(60, "I Piotra")
            map.put(61, "II Piotra")
            map.put(62, "I Jana")
            map.put(63, "II Jana")
            map.put(64, "III Jana")
            map.put(65, "Judy")
            map.put(66, "Objawienie")

            return map
        }

    val CHINESE_NUMBER_NAME_MAP = chineseBooks
    private val chineseBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "创世记")
            map.put(2, "出埃及")
            map.put(3, "利未记")
            map.put(4, "民数记")
            map.put(5, "申命记")
            map.put(6, "约书亚记")
            map.put(7, "士师记")
            map.put(8, "路得记")
            map.put(9, "撒母耳记上")
            map.put(10, "撒母耳记下")
            map.put(11, "列王纪上")
            map.put(12, "列王纪下")
            map.put(13, "历代志上")
            map.put(14, "历代志下")
            map.put(15, "以斯拉记")
            map.put(16, "尼希米记")
            map.put(17, "以斯帖记")
            map.put(18, "约伯记")
            map.put(19, "诗篇")
            map.put(20, "箴言")
            map.put(21, "传道书")
            map.put(22, "雅歌")
            map.put(23, "以赛亚书")
            map.put(24, "耶利米书")
            map.put(25, "耶利米哀歌")
            map.put(26, "以西结书")
            map.put(27, "但以理书")
            map.put(28, "何西阿书")
            map.put(29, "约珥书")
            map.put(30, "阿摩司书")
            map.put(31, "俄巴底亚书")
            map.put(32, "约拿书")
            map.put(33, "弥迦书")
            map.put(34, "那鸿书")
            map.put(35, "哈巴谷书")
            map.put(36, "西番雅书")
            map.put(37, "哈该书")
            map.put(38, "撒迦利亚书")
            map.put(39, "玛拉基书")
            map.put(40, "马太福音")
            map.put(41, "马可福音")
            map.put(42, "路加福音")
            map.put(43, "约翰福音")
            map.put(44, "使徒行传")
            map.put(45, "罗马书")
            map.put(46, "哥林多前书")
            map.put(47, "哥林多后书")
            map.put(48, "加拉太书")
            map.put(49, "以弗所书")
            map.put(50, "腓立比书")
            map.put(51, "歌罗西书")
            map.put(52, "帖撒罗尼迦前书")
            map.put(53, "帖撒罗尼迦后书")
            map.put(54, "提摩太前书")
            map.put(55, "提摩太后书")
            map.put(56, "提多书")
            map.put(57, "腓利门书")
            map.put(58, "希伯来书")
            map.put(59, "雅各书")
            map.put(60, "彼得前书")
            map.put(61, "彼得后书")
            map.put(62, "约翰一书")
            map.put(63, "约翰二书")
            map.put(64, "约翰三书")
            map.put(65, "犹大书")
            map.put(66, "启示录")

            return map
        }

    val KOREAN_NUMBER_NAME_MAP = koreanBooks
    private val koreanBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "창세기")
            map.put(2, "출애굽기")
            map.put(3, "레위기")
            map.put(4, "민수기")
            map.put(5, "신명기")
            map.put(6, "여호수아")
            map.put(7, "사사기")
            map.put(8, "룻기")
            map.put(9, "사무엘상")
            map.put(10, "사무엘하")
            map.put(11, "열왕기상")
            map.put(12, "열왕기하")
            map.put(13, "역대상")
            map.put(14, "역대하")
            map.put(15, "에스라")
            map.put(16, "느헤미야")
            map.put(17, "에스더")
            map.put(18, "욥기")
            map.put(19, "시편")
            map.put(20, "잠언")
            map.put(21, "전도서")
            map.put(22, "아가")
            map.put(23, "이사야")
            map.put(24, "예레미야")
            map.put(25, "예레미야애가")
            map.put(26, "에스겔")
            map.put(27, "다니엘")
            map.put(28, "호세아")
            map.put(29, "요엘")
            map.put(30, "아모스")
            map.put(31, "오바댜")
            map.put(32, "요나")
            map.put(33, "미가")
            map.put(34, "나훔")
            map.put(35, "하박국")
            map.put(36, "스바냐")
            map.put(37, "학개")
            map.put(38, "스가랴")
            map.put(39, "말라기")
            map.put(40, "마태복음")
            map.put(41, "마가복음")
            map.put(42, "누가복음")
            map.put(43, "요한복음")
            map.put(44, "사도행전")
            map.put(45, "로마서")
            map.put(46, "고린도전서")
            map.put(47, "고린도후서")
            map.put(48, "갈라디아서")
            map.put(49, "에베소서")
            map.put(50, "빌립보서")
            map.put(51, "골로새서")
            map.put(52, "데살로니가전서")
            map.put(53, "데살로니가후서")
            map.put(54, "디모데전서")
            map.put(55, "디모데후서")
            map.put(56, "디도서")
            map.put(57, "빌레몬서")
            map.put(58, "히브리서")
            map.put(59, "야고보서")
            map.put(60, "베드로전서")
            map.put(61, "베드로후서")
            map.put(62, "요한1서")
            map.put(63, "요한2서")
            map.put(64, "요한3서")
            map.put(65, "유다서")
            map.put(66, "요한계시록")

            return map
        }

    val JAPANESE_JC_NUMBER_NAME_MAP = japaneseJcBooks
    private val japaneseJcBooks: SparseArray<String>
        get(){
            val map = SparseArray<String>(67)

            map.put(1, "創世記")
            map.put(2, "出エジプト記")
            map.put(3, "レビ記")
            map.put(4, "民数記")
            map.put(5, "申命記")
            map.put(6, "ヨシュア記")
            map.put(7, "士師記")
            map.put(8, "ルツ記")
            map.put(9, "サムエル記上")
            map.put(10, "サムエル記下")
            map.put(11, "列王記上")
            map.put(12, "列王記下")
            map.put(13, "歴代誌上")
            map.put(14, "歴代誌下")
            map.put(15, "エズラ記")
            map.put(16, "ネヘミヤ記")
            map.put(17, "エステル記")
            map.put(18, "ヨブ記")
            map.put(19, "詩篇")
            map.put(20, "箴言")
            map.put(21, "伝道の書")
            map.put(22, "雅歌")
            map.put(23, "イザヤ書")
            map.put(24, "エレミヤ書")
            map.put(25, "哀歌")
            map.put(26, "エゼキエル書")
            map.put(27, "ダニエル書")
            map.put(28, "ホセア書")
            map.put(29, "ヨエル書")
            map.put(30, "アモス書")
            map.put(31, "オバデヤ書")
            map.put(32, "ヨナ書")
            map.put(33, "ミカ書")
            map.put(34, "ナホム書")
            map.put(35, "ハバクク書")
            map.put(36, "ゼパニヤ書")
            map.put(37, "ハガイ書")
            map.put(38, "ゼカリヤ書")
            map.put(39, "マラキ書")
            map.put(40, "マタイによる福音書")
            map.put(41, "マルコによる福音書")
            map.put(42, "ルカによる福音書")
            map.put(43, "ヨハネによる福音書")
            map.put(44, "使徒行伝")
            map.put(45, "ローマ人への手紙")
            map.put(46, "コリント人への第一の手紙")
            map.put(47, "コリント人への第二の手紙")
            map.put(48, "ガラテヤ人への手紙")
            map.put(49, "エペソ人への手紙")
            map.put(50, "ピリピ人への手紙")
            map.put(51, "コロサイ人への手紙")
            map.put(52, "テサロニケ人への第一の手紙")
            map.put(53, "テサロニケ人への第二の手紙")
            map.put(54, "テモテへの第一の手紙")
            map.put(55, "テモテへの第二の手紙")
            map.put(56, "テトスへの手紙")
            map.put(57, "ピレモンへの手紙")
            map.put(58, "ヘブル人への手紙")
            map.put(59, "ヤコブの手紙")
            map.put(60, "ペテロの第一の手紙")
            map.put(61, "ペテロの第二の手紙")
            map.put(62, "ヨハネの第一の手紙")
            map.put(63, "ヨハネの第二の手紙")
            map.put(64, "ヨハネの第三の手紙")
            map.put(65, "ユダの手紙")
            map.put(66, "ヨハネの黙示録")
            return map
        }
}
