package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.ItemBean;

public class FileUpload {

	public ItemBean FileUp(HttpServletRequest request, String path) throws Exception {
		ItemBean item = new ItemBean();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new ServletException("ファイルのアップロードに失敗しました: enctypeがmultipart/form-dataではありません。");
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(50 * 1024 * 1024); // 50MBまで許可

		// ディレクトリが存在しない場合は作成
		File directory = new File(path);
		if (!directory.exists()) {
			boolean dirCreated = directory.mkdirs();
			if (!dirCreated) {
				throw new ServletException("ディレクトリの作成に失敗しました: " + path);
			}
		}

		System.out.println("Upload Path: " + path);

		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem itemField : items) {
				if (itemField.isFormField()) {
					// フォームフィールドの場合
					switch (itemField.getFieldName()) {
					case "itemNo":
						String itemNo = itemField.getString();
						if (itemNo != null && !itemNo.isEmpty()) {
							item.setItemNo(Integer.parseInt(itemNo));
						} else {
							item.setItemNo(0); // デフォルト値を設定（必要に応じて変更）
						}
						break;
					case "itemName":
						item.setItemName(itemField.getString(StandardCharsets.UTF_8.name()));
						break;
					case "itemPrice":
						item.setItemPrice(Integer.parseInt(itemField.getString()));
						break;
					case "stockCount":
						item.setStockCount(Integer.parseInt(itemField.getString()));
						break;
					case "specialItem":
						item.setSpecialItem(Integer.parseInt(itemField.getString()));
						break;
					case "datetime":
						item.setUpdateTime(Timestamp.valueOf(itemField.getString()));
						break;
					}
				} else {
					// ファイルフィールドの場合
					if (!itemField.getName().isEmpty() && isValidExtension(itemField.getName())) {
						String fileName = new File(itemField.getName()).getName();
						String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());
						String filePath = "files" + File.separator + decodedFileName; // 相対パスとして保存
						File uploadFile = new File(path + File.separator + decodedFileName);
						try (InputStream inputStream = itemField.getInputStream();
								FileOutputStream outputStream = new FileOutputStream(uploadFile)) {
							int read = 0;
							final byte[] bytes = new byte[1024];
							while ((read = inputStream.read(bytes)) != -1) {
								outputStream.write(bytes, 0, read);
							}
						}
						item.setItemImagePath(filePath);
					} else if (itemField.getFieldName().equals("csvFile")) {
						// CSVファイルの処理
						if (!itemField.getName().isEmpty() && isValidCsvExtension(itemField.getName())) {
							String fileName = new File(itemField.getName()).getName();
							String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());
							String filePath = path + File.separator + decodedFileName;
							File uploadFile = new File(filePath);
							try (InputStream inputStream = itemField.getInputStream();
									FileOutputStream outputStream = new FileOutputStream(uploadFile)) {
								int read = 0;
								final byte[] bytes = new byte[1024];
								while ((read = inputStream.read(bytes)) != -1) {
									outputStream.write(bytes, 0, read);
								}
							}
							// CSVファイルのパスをItemBeanに設定
							item.setCsvFilePath(filePath);
						} else {
							throw new ServletException("無効なファイル形式です。");
						}
					} else {
						throw new ServletException("無効なファイル形式です。");
					}
				}
			}
		} catch (FileUploadException | IOException ex) {
			throw new ServletException("ファイルのアップロードに失敗しました: " + ex.getMessage());
		}

		return item;
	}

	private boolean isValidExtension(String fileName) {
		String[] validExtensions = { "png", "jpeg", "jpg" };
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		for (String validExtension : validExtensions) {
			if (validExtension.equals(extension)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidCsvExtension(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		return "csv".equals(extension);
	}
}