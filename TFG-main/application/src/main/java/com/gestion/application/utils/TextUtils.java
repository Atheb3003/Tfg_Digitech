package com.gestion.application.utils;

import java.text.Normalizer;

public class TextUtils {

  public static String normalizeTerm(String input) {
    return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
  }
}
