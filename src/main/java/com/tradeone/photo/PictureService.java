package com.tradeone.photo;

import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

  @Transactional
  void addPictures(List<MultipartFile> files, String description)
      throws IOException;

  Page<Picture> findByPartialAlt(String part, Pageable pageable);

  Picture findById(Long pictureId);
}
