package com.careerfocus.dao;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.careerfocus.constants.Constants;
import com.careerfocus.entity.States;

@Repository
public class CommonDAO {

	private JdbcTemplate template;

	@Autowired
	public CommonDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<States> getStates() {
		String query = "SELECT * FROM states";

		return template.query(query,
				(ResultSet result, int arg1) -> new States(result.getInt("state_id"), result.getString("name")));
	}

	public long getTimeDifferenceInSec(Date timeLastUpdated) {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Date date = new Date();
		String timeCurrent = format.format(date);
		String timePrevious = format.format(timeLastUpdated);

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(timePrevious);
			d2 = format.parse(timeCurrent);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Get msec from each, and subtract.
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000);
		System.out.println("Time in seconds: " + diffSeconds + " seconds.");
		System.out.println("Time in minutes: " + diffMinutes + " minutes.");
		System.out.println("Time in hours: " + diffHours + " hours.");
		return diffSeconds;
	}

	public List<Map<String, Object>> getCoachingTypes() {
		String query = "SELECT coaching_type_id as coachingTypeId, name FROM coaching_type";
		return template.queryForList(query);
	}

	public boolean hasEmail(String emailId) {
		String query = "SELECT username FROM user WHERE username = ?";
		if (template.queryForList(query).size() > 0)
			return true;
		else
			return false;
	}

	public String getPasswordForAUser(int userId) {
		String query = "SELECT password FROM user WHERE user_id = ?";
		return template.queryForObject(query, String.class, userId);
	}

	public String getEmailIdFromUserId(int userId) {
		String query = "SELECT username FROM user WHERE user_id = ?";
		return template.queryForObject(query, String.class, userId);
	}

	public int getUserIdFromEmailId(String emailId) {
		String query = "SELECT user_id FROM user WHERE username = ?";
		return template.queryForObject(query, Integer.class, emailId);
	}

	public int getStatusFromEmailId(String emailId) {
		String query = "SELECT status FROM user WHERE username = ?";
		return template.queryForObject(query, Integer.class, emailId);
	}
	

	public byte[] resizeImage(MultipartFile image, boolean preserveAlpha) {
		byte[] imageInByte = null;
		byte[] tempFile = null;
		try {
			imageInByte = image.getBytes();
			String imageName = image.getOriginalFilename();
			System.out.println("imageName = " + imageName);
			int scaledWidth = Constants.SCALED_WIDTH;
			int scaledHeight = Constants.SCALED_HEIGHT;

			InputStream is = new ByteArrayInputStream(imageInByte);
			BufferedImage originalImage = ImageIO.read(is);
			float ratio = (float) originalImage.getWidth() / originalImage.getHeight();

			if (ratio <= Constants.DEFAULT_ASPECT_RATIO) {
				scaledWidth = (int) (ratio * scaledWidth);
				scaledHeight = Constants.SCALED_HEIGHT;
			} else {
				scaledWidth = Constants.SCALED_WIDTH;
				scaledHeight = (int) (scaledHeight / ratio);
			}

			int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
			BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
			Graphics2D g = scaledBI.createGraphics();
			if (preserveAlpha) {
				g.setComposite(AlphaComposite.Src);
			}
			g.drawImage(originalImage, Constants.FALSE, Constants.FALSE, scaledWidth, scaledHeight, null);
			g.dispose();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			String extension = ".jpg";
			if (scaledBI.getType() == Constants.IMAGE_TYPE_PNG) {
				ImageIO.write(scaledBI, "png", os);
				extension = "png";
			} else {
				ImageIO.write(scaledBI, "jpg", os);
			}

			InputStream in = new ByteArrayInputStream(os.toByteArray());
			tempFile = stream2file(in, imageName, extension);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}

	public static byte[] stream2file(InputStream in, String imageName, String extension) throws IOException {
		final File tempFile = File.createTempFile(imageName, extension);
		tempFile.deleteOnExit();
		try {
			FileOutputStream out = new FileOutputStream(tempFile);
			IOUtils.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file2Bytes(tempFile);
	}

	public static byte[] file2Bytes(File pic) {
		try {
			BufferedImage originalImage = ImageIO.read(pic);
			System.out.println("height = " + originalImage.getHeight());
			// originalImage = Scalr.resize(originalImage, Scalr.Method.QUALITY,
			// Scalr.Mode.FIT_EXACT,
			// Constants.SCALED_WIDTH, Constants.SCALED_HEIGHT);
			// To save with original ratio uncomment next line and comment the
			// above.
			originalImage = Scalr.resize(originalImage, Constants.SCALED_WIDTH, Constants.SCALED_HEIGHT);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			System.out.println("Bytes = " + imageInByte);
			return imageInByte;
		} catch (Exception e) {
			return null;
		}
	}

	// public DateTime getCurrentIST() {
	// String TIME_SERVER = "time-a.nist.gov";
	// NTPUDPClient timeClient = new NTPUDPClient();
	// InetAddress inetAddress;
	// DateTime time = null;
	// try {
	// inetAddress = InetAddress.getByName(TIME_SERVER);
	// TimeInfo timeInfo;
	// timeInfo = timeClient.getTime(inetAddress);
	// long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
	// time = new DateTime(returnTime).withZone(DateTimeZone.UTC);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return time;
	// }

}
