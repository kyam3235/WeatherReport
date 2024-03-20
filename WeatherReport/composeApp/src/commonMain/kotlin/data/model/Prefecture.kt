package data.model

sealed class Prefecture {
    // 北海道
    data object Hokkaido : Prefecture()

    // 東北
    data object Aomori : Prefecture()
    data object Iwate : Prefecture()
    data object Miyagi : Prefecture()
    data object Akita : Prefecture()
    data object Yamagata : Prefecture()
    data object Fukushima : Prefecture()

    // 関東
    data object Ibaraki : Prefecture()
    data object Tochigi : Prefecture()
    data object Gunma : Prefecture()
    data object Saitama : Prefecture()
    data object Chiba : Prefecture()
    data object Tokyo : Prefecture()
    data object Kanagawa : Prefecture()
    data object Yamanashi : Prefecture()
    data object Nagano : Prefecture()

    // 北陸
    data object Niigata : Prefecture()
    data object Toyama : Prefecture()
    data object Ishikawa : Prefecture()
    data object Fukui : Prefecture()

    // 中部
    data object Gifu : Prefecture()
    data object Shizuoka : Prefecture()
    data object Aichi : Prefecture()
    data object Mie : Prefecture()

    // 近畿
    data object Shiga : Prefecture()
    data object Kyoto : Prefecture()
    data object Osaka : Prefecture()
    data object Hyogo : Prefecture()
    data object Nara : Prefecture()
    data object Wakayama : Prefecture()

    // 中国
    data object Tottori : Prefecture()
    data object Shimane : Prefecture()
    data object Okayama : Prefecture()
    data object Hiroshima : Prefecture()
    data object Yamaguchi : Prefecture()

    // 四国
    data object Tokushima : Prefecture()
    data object Kagawa : Prefecture()
    data object Ehime : Prefecture()
    data object Kochi : Prefecture()

    // 九州
    data object Fukuoka : Prefecture()
    data object Saga : Prefecture()
    data object Nagasaki : Prefecture()
    data object Kumamoto : Prefecture()
    data object Oita : Prefecture()
    data object Miyazaki : Prefecture()
    data object Kagoshima : Prefecture()

    // 沖縄
    data object Okinawa : Prefecture()
}