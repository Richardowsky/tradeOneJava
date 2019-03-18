package com.tradeone.photo;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

  Optional<Picture> findById(Long pictureId);

  Page<Picture> findByAltContaining(String part, Pageable pageable);

}
