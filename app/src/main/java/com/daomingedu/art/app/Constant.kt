package com.daomingedu.art.app

import android.os.Environment
import java.io.File

/**
 * @创建者 chendong
 * @创建时间 2019/2/22 9:58
 * @描述
 */
interface Constant {
	companion object {
		const val KEY = "7e030e749d199159dc16931b46f1479e"
		const val LOGIN_STATUS = "loginStatus"
		const val SESSION_ID = "sessionId"
		const val LOGIN_NEED_KILL_OTHER = "login_need_kill_other"
		const val USER_INFO_STRING = "userInfoString"
		const val CHOOSE_PROFESSION_RESULT_CODE = 100
		const val MUSICAL_INSTRUMENT_RESULT_CODE = 101
		const val MUSICAL_INSTRUMENT_REQUEST_CODE = 102

		const val CLASS_ID_EXTRA = "classIdExtra"
		const val GRADE_ID_EXTRA = "gradeIdExtra"
		const val GRADE_IMAGE_EXTRA = "gradeImageExtra"
		const val GRADE_NAME_EXTRA = "gradeNameExtra"
		const val GRADE_DESC_EXTRA = "gradeDescExtra"
		const val MUSIC_ID_EXTRA = "musicIdExtra"
		const val MUSIC_NAME_EXTRA = "musicNameExtra"
		const val MUSIC_COVER_EXTRA = "musicCoverExtra"
		const val MUSIC_AUTHOR_EXTRA = "musicAuthorExtra"
		const val X_EXTRA = "xExtra"
		const val Y_EXTRA = "yExtra"
		const val TYPE_EXTRA = "typeExtra"
		const val CHECK_EXTRA = "checkExtra"
		const val TEXT_EXTRA = "textExtra"
		const val IMAGES_ID_EXTRA = "imagesIdExtra"
		const val NOTE_ID_EXTRA = "noteIdExtra"
		const val BASE_64_EXTRA = "base64Extra"
		const val IS_AUDIO_EXTRA = "isAudioExtra"
		const val FROM_LOGIN_EXTRA = "fromLoginExtra"
		const val SEARCH_KEY_EXTRA = "searchKeyExtra"
		const val FROM_LOCAL_EXTRA = "fromLocalExtra"
		const val URL_EXTRA = "urlExtra"
		const val TITLE_EXTRA = "titleExtra"
		/**
		 * 专业id集合
		 */
		const val MAJOR_IDS = "majorIds"
		/**
		 * 当前选中的专业id
		 */
		const val MAJOR_ID = "majorId"
		/**
		 * 当前选中专业名称
		 */
		const val MAJOR_NAME = "majorName"
		/**
		 * 当前选中专业图片
		 */
		const val MAJOR_IMAGE = "majorImage"
		/**
		 * 图片上传大小限制
		 */
		const val IMAGE_FILE_SIZE = "imageFileSize"
		/**
		 * 音频上传大小限制
		 */
		const val RECORD_FILE_SIZE = "recordFileSize"
		/**
		 * 视频上传大小限制
		 */
		const val VIDEO_FILE_SIZE = "videoFileSize"
		/**
		 * 文件存储对象前缀
		 */
		const val COS_PATH = "cosPath"

		private val SMART_NOTE_PAD_ROOT_FOLDER =
			Environment.getExternalStorageDirectory().toString() + File.separator + "smartNotePad"//文件根目录
		val APK_FOLDER = SMART_NOTE_PAD_ROOT_FOLDER + File.separator + "apk"//apk下载目录
		val AUDIO_FOLDER = SMART_NOTE_PAD_ROOT_FOLDER + File.separator + "audio"//音频下载目录
		val VIDEO_FOLDER = SMART_NOTE_PAD_ROOT_FOLDER + File.separator + "video"//视频下载目录
	}
}