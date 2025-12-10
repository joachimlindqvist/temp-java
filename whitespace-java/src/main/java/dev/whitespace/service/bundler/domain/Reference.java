package dev.whitespace.service.bundler.domain;

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

   @Override
   public String toString() {
	   return url().toString();
   }

   public static boolean isExternal(String ref) {
	   return !ref.startsWith("#");
   }
}
