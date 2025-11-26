package dev.whitespace.core.service.bundler.domain;

import lombok.RequiredArgsConstructor;

import java.net.URI;

@RequiredArgsConstructor
public class Reference {
   private final String ref;
   private final URI base;
   private final String resourceType;

   public URI url() {
       return base.resolve(ref);
   }
}
