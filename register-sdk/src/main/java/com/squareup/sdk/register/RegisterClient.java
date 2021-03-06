/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.squareup.sdk.register;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Provides methods for interacting with the Square Register app, such as by generating
 * {@code Intent}s that initiate transactions.
 */
public interface RegisterClient {

  /**
   * Creates an {@code Intent} that can be used to initiate a Square Register
   * transaction. Provide the created {@code Intent} to the
   * {@link android.app.Activity#startActivityForResult(Intent, int)} to
   * initiate the transaction.
   *
   * @throws android.content.ActivityNotFoundException if Square Register is not installed.
   * @throws NullPointerException if chargeRequest is null.
   * @see RegisterSdk
   */
  @NonNull Intent createChargeIntent(@NonNull ChargeRequest chargeRequest);

  /**
   * @return {@code true} if a version of Square Register that supports the Register API is installed,
   * {@code false} otherwise.
   */
  boolean isRegisterInstalled();

  /**
   * Launches the Square Register application. This is equivalent to pressing the home button
   * and opening the Register app. It is useful for handling errors that require the user to
   * complete an action within Register, such as completing a transaction after receiving a
   * {@link ChargeRequest.ErrorCode#TRANSACTION_ALREADY_IN_PROGRESS} error.
   *
   * @throws android.content.ActivityNotFoundException if Square Register is not installed.
   */
  void launchRegister();

  /**
   * Opens the Square Register install page in the Google Play Store. The Play Store
   * activity is started using the application context.
   */
  void openRegisterPlayStoreListing();

  /**
   * Use this method to parse the data {@link Intent} passed in
   * {@link android.app.Activity#onActivityResult(int, int, Intent)} when {@code resultCode}
   * is equal to {@link android.app.Activity#RESULT_OK} (successful transaction).
   */
  @NonNull ChargeRequest.Success parseChargeSuccess(@NonNull Intent data);

  /**
   * Use this method to parse the data {@link Intent} passed in
   * {@link android.app.Activity#onActivityResult(int, int, Intent)} when {@code resultCode}
   * is equal to {@link android.app.Activity#RESULT_CANCELED} (canceled transaction).
   */
  @NonNull ChargeRequest.Error parseChargeError(@NonNull Intent data);
}
