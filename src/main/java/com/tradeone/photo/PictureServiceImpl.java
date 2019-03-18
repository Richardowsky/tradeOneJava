package com.tradeone.photo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {

  @Autowired
  private PictureRepository pictureRepository;
  @Autowired
  private PictureServiceFS pictureServiceFS;

  @Override
  @Transactional
  public void addPictures(List<MultipartFile> files, String description)
      throws IOException {
    for (MultipartFile file : files) {
      InputStream inputStream = file.getInputStream();
      String originalFilename = file.getOriginalFilename();

      String fileName = pictureServiceFS.savePicture(inputStream, originalFilename);
      Picture picture = new Picture(fileName, description);
      pictureRepository.save(picture);
    }
  }

  @Override
  public Page<Picture> findByPartialAlt(String part, Pageable pageable) {
    return pictureRepository.findByAltContaining(part, pageable);
  }

  @Override
  public Picture findById(Long pictureId) {
    return pictureRepository.findById(pictureId)
        .orElseThrow(() -> new RuntimeException("PictureNotFoundException"));
  }
}
