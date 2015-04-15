package com.fithou.vnnews.models;

import java.util.Vector;

public class AppConfig {
	public static String SERVER = "http://tablet-services.azurewebsites.net/Home";
	public static String URL_NONG = SERVER
			+ "/CatVietV6?cat=Th%E1%BA%BF+gi%E1%BB%9Bi";
	public static Vector<NewsItem> TIN_NONG = new Vector<NewsItem>();

	public static String URL_THEGIOI = SERVER
			+ "/CatVietV6?cat=Th%E1%BA%BF+gi%E1%BB%9Bi";
	public static Vector<NewsItem> THE_GIOI = new Vector<NewsItem>();

	public static String URL_VANHOA = SERVER
			+ "/CatVietV6?cat=V%C4%83n%20h%C3%B3a";
	public static Vector<NewsItem> VAN_HOA = new Vector<NewsItem>();

	public static String URL_XAHOI = SERVER
			+ "/CatVietV6?cat=X%C3%A3%20h%E1%BB%99i";
	public static Vector<NewsItem> XA_HOI = new Vector<NewsItem>();

	public static String URL_KINHTE = SERVER
			+ "/CatVietV6?cat=Kinh%20t%E1%BA%BF";
	public static Vector<NewsItem> KINH_TE = new Vector<NewsItem>();

	public static String URL_CONGNGHE = SERVER
			+ "/CatVietV6?cat=C%C3%B4ng%20ngh%E1%BB%87";
	public static Vector<NewsItem> CONG_NGHE = new Vector<NewsItem>();

	public static String URL_THETHAO = SERVER
			+ "/CatVietV6?cat=Th%E1%BB%83%20thao";
	public static Vector<NewsItem> THE_THAO = new Vector<NewsItem>();

	public static String URL_GIAITRI = SERVER
			+ "/CatVietV6?cat=Gi%E1%BA%A3i%20tr%C3%AD";
	public static Vector<NewsItem> GIAI_TRI = new Vector<NewsItem>();

	public static String URL_PHAPLUAT = SERVER
			+ "/CatVietV6?cat=Ph%C3%A1p%20lu%E1%BA%ADt";
	public static Vector<NewsItem> PHAP_LUAT = new Vector<NewsItem>();

	public static String URL_GIAODUC = SERVER
			+ "/CatVietV6?cat=Gi%C3%A1o%20d%E1%BB%A5c";
	public static Vector<NewsItem> GIAO_DUC = new Vector<NewsItem>();

	public static String URL_SUCKHOE = SERVER
			+ "/CatVietV6?cat=S%E1%BB%A9c%20kh%E1%BB%8Fe";
	public static Vector<NewsItem> SUC_KHOE = new Vector<NewsItem>();

	public static String URL_OTOXEMAY = SERVER
			+ "/CatVietV6?cat=%C3%94%20t%C3%B4%20-%20Xe%20m%C3%A1y";
	public static Vector<NewsItem> OTO_XEMAY = new Vector<NewsItem>();

	public static String URL_NHADAT = SERVER
			+ "/CatVietV6?cat=Nh%C3%A0%20%C4%91%E1%BA%A5t";
	public static Vector<NewsItem> NHA_DAT = new Vector<NewsItem>();

	public static String URL_DOCNHIEU = SERVER
			+ "/CatVietV6?cat=%C4%90%E1%BB%8Dc%20nhi%E1%BB%81u";
	public static Vector<NewsItem> DOC_NHIEU = new Vector<NewsItem>();

	public static String URL_GETNEWS = SERVER + "/GetNewsV6?id=";

	//
	public static Vector<NewsItem> FAVORITE = new Vector<NewsItem>();
	public static Vector<NewsItem> SAVED = new Vector<NewsItem>();

	public static String FOLDER_SAVE = "vnnewsdata";

}
